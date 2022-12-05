package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Response;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OAuthResponse {
    private OAuthResponse() {
    }

    static <D, E> Response<D, E> response(String token, @Nullable D data, @Nullable E error) {
        return (data == null && error == null) ?
                new OAuthResponse.Exception<>(token, new IllegalArgumentException()) :
                ((error == null) ?
                        new OAuthResponse.Success<>(token, data) :
                        new OAuthResponse.Failure<>(token, error)
                );
    }

    static <D, E> Response<D, E> exception(String token, Throwable throwable) {
        return new OAuthResponse.Exception<>(token, throwable);
    }

    record Success<D, E>(String token, D data) implements Response<D, E> {

        @Override
        public E error() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Response<D, E> succeed(Consumer<D> data) {
            data.accept(this.data);

            return this;
        }

        @Override
        public Response<D, E> fail(Consumer<E> error) {
            return this;
        }

        @Override
        public Response<D, E> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return true;
        }
    }

    record Failure<D, E>(String token, E error) implements Response<D, E> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Response<D, E> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Response<D, E> fail(Consumer<E> error) {
            error.accept(this.error);

            return this;
        }

        @Override
        public Response<D, E> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }

    record Exception<D, E>(String token, Throwable throwable) implements Response<D, E> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public E error() {
            return null;
        }

        @Override
        public Response<D, E> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Response<D, E> fail(Consumer<E> error) {
            return this;
        }

        @Override
        public Response<D, E> except(Consumer<Throwable> throwable) {
            throwable.accept(this.throwable);

            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }
}
