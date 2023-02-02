package io.hanbings.flows.common;

import io.hanbings.flows.common.interfaces.State;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class OAuthState implements State {
    final long expire;
    final Supplier<String> state;

    Map<String, Long> states = new ConcurrentHashMap<>();

    @Override
    public String add() {
        String state = this.state.get();
        states.put(state, System.currentTimeMillis() + expire);

        return state;
    }

    @Override
    public void add(String state) {
        states.put(state, System.currentTimeMillis() + expire);
    }

    @Override
    public boolean has(String state) {
        return states.containsKey(state) && (states.get(state) < System.currentTimeMillis());
    }

    @Override
    public boolean fire(String state) {
        if (!states.containsKey(state)) {
            return false;
        }

        return states.remove(state) < System.currentTimeMillis();
    }

    @Override
    public void clean() {
        // current time
        long time = System.currentTimeMillis();
        // temp
        Set<String> delete = new HashSet<>();

        // collect expire keys
        states.forEach((k, v) -> {
            if (v < time) {
                delete.add(k);
            }
        });

        // delete
        delete.forEach(k -> states.remove(k));
    }

    @Override
    public void empty() {
        states.clear();
    }
}
