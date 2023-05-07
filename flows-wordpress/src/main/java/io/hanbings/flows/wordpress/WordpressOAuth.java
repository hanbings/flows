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

package io.hanbings.flows.wordpress;

import io.hanbings.flows.common.OAuth;

import java.util.List;
import java.util.Map;

public class WordpressOAuth extends OAuth<WordpressAccess, WordpressAccess.Wrong> {
    private WordpressOAuth() {
        super(null, null, null, null);
    }

    public WordpressOAuth(String client, String secret, String redirect) {
        super(
                "https://twitter.com/i/oauth2/authorize",
                "https://api.twitter.com/2/oauth2/token",
                List.of(),
                Map.of()
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }

    public WordpressOAuth(String client, String secret, String redirect,
                          List<String> scopes, Map<String, String> params) {
        super(
                "https://twitter.com/i/oauth2/authorize",
                "https://api.twitter.com/2/oauth2/token",
                scopes,
                params
        );

        this.client(client);
        this.secret(secret);
        this.redirect(redirect);
    }
}
