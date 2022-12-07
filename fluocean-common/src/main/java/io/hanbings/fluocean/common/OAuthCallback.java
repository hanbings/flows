package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Callback;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OAuthCallback {
    private OAuthCallback() {
    }

    public static <D, E> Callback<D, E> response(String token, @Nullable D data, @Nullable E error) {
        return (data == null && error == null) ?
                new OAuthCallback.Exception<>(token, new IllegalArgumentException()) :
                ((error == null) ?
                        new OAuthCallback.Success<>(token, data) :
                        new OAuthCallback.Failure<>(token, error)
                );
    }

    public static <D, E> Callback<D, E> exception(String token, Throwable throwable) {
        return new OAuthCallback.Exception<>(token, throwable);
    }

    record Success<D, E>(String token, D data) implements Callback<D, E> {

        @Override
        public E error() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Callback<D, E> succeed(Consumer<D> data) {
            data.accept(this.data);

            return this;
        }

        @Override
        public Callback<D, E> fail(Consumer<E> error) {
            return this;
        }

        @Override
        public Callback<D, E> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return true;
        }
    }

    record Failure<D, E>(String token, E error) implements Callback<D, E> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Callback<D, E> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Callback<D, E> fail(Consumer<E> error) {
            error.accept(this.error);

            return this;
        }

        @Override
        public Callback<D, E> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }

    record Exception<D, E>(String token, Throwable throwable) implements Callback<D, E> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public E error() {
            return null;
        }

        @Override
        public Callback<D, E> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Callback<D, E> fail(Consumer<E> error) {
            return this;
        }

        @Override
        public Callback<D, E> except(Consumer<Throwable> throwable) {
            throwable.accept(this.throwable);

            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }
}
