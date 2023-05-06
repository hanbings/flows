/*
 * Copyright 2023 Flows
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
