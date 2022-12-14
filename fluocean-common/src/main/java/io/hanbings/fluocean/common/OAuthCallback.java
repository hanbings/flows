package io.hanbings.fluocean.common;

import io.hanbings.fluocean.common.interfaces.Callback;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OAuthCallback {
    private OAuthCallback() {
    }

    public static <D, W> Callback<D, W> response(String token, @Nullable D data, @Nullable W wrong) {
        return (data == null && wrong == null) ?
                new OAuthCallback.Exception<>(token, new IllegalArgumentException()) :
                ((wrong == null) ?
                        new OAuthCallback.Success<>(token, data) :
                        new OAuthCallback.Failure<>(token, wrong)
                );
    }

    public static <D, W> Callback<D, W> exception(String token, Throwable throwable) {
        return new OAuthCallback.Exception<>(token, throwable);
    }

    record Success<D, W>(String token, D data) implements Callback<D, W> {

        @Override
        public W wrong() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Callback<D, W> succeed(Consumer<D> data) {
            data.accept(this.data);

            return this;
        }

        @Override
        public Callback<D, W> fail(Consumer<W> wrong) {
            return this;
        }

        @Override
        public Callback<D, W> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return true;
        }
    }

    record Failure<D, W>(String token, W wrong) implements Callback<D, W> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public Throwable throwable() {
            return null;
        }

        @Override
        public Callback<D, W> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Callback<D, W> fail(Consumer<W> wrong) {
            wrong.accept(this.wrong);

            return this;
        }

        @Override
        public Callback<D, W> except(Consumer<Throwable> exception) {
            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }

    record Exception<D, W>(String token, Throwable throwable) implements Callback<D, W> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public W wrong() {
            return null;
        }

        @Override
        public Callback<D, W> succeed(Consumer<D> data) {
            return this;
        }

        @Override
        public Callback<D, W> fail(Consumer<W> wrong) {
            return this;
        }

        @Override
        public Callback<D, W> except(Consumer<Throwable> throwable) {
            throwable.accept(this.throwable);

            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }
}
