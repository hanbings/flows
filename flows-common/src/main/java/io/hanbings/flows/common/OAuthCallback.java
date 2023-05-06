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

import io.hanbings.flows.common.interfaces.Callback;
import io.hanbings.flows.common.interfaces.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OAuthCallback {
    private OAuthCallback() {
    }

    public static <D, W> Callback<D, W> response(@NotNull Response response) {
        return new OAuthCallback.Complete<>(response);
    }

    public static <D, W> Callback<D, W> response(
            String token, @Nullable D data, @Nullable W wrong, @NotNull Response response
    ) {
        return (data == null && wrong == null) ?
                new OAuthCallback.Exception<>(token, new IllegalArgumentException(), null) :
                ((wrong == null) ?
                        new OAuthCallback.Success<>(token, data, response) :
                        new OAuthCallback.Failure<>(token, wrong, response)
                );
    }

    public static <D, W> Callback<D, W> exception(String token, Throwable throwable) {
        return new OAuthCallback.Exception<>(token, throwable, null);
    }

    record Complete<D, W>(Response response) implements Callback<D, W> {
        @Override
        public D data() {
            return null;
        }

        @Override
        public W wrong() {
            return null;
        }

        @Override
        public String token() {
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
        public Callback<D, W> completed(Consumer<Response> response) {
            response.accept(this.response);

            return this;
        }

        @Override
        public Callback<D, W> fail(Consumer<W> wrong) {
            return this;
        }

        @Override
        public Callback<D, W> except(Consumer<Throwable> throwable) {
            return this;
        }

        @Override
        public boolean success() {
            return false;
        }
    }

    record Success<D, W>(String token, D data, Response response) implements Callback<D, W> {

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
        public Callback<D, W> completed(Consumer<Response> response) {
            response.accept(this.response);

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

    record Failure<D, W>(String token, W wrong, Response response) implements Callback<D, W> {
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
        public Callback<D, W> completed(Consumer<Response> response) {
            response.accept(this.response);

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

    record Exception<D, W>(String token, Throwable throwable, Response response) implements Callback<D, W> {
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
        public Callback<D, W> completed(Consumer<Response> response) {
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
