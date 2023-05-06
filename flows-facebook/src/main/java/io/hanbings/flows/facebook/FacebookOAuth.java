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

package io.hanbings.flows.facebook;

import io.hanbings.flows.common.OAuth;

public class FacebookOAuth extends OAuth<FacebookAccess, FacebookAccess.Wrong> {
    private FacebookOAuth() {
        super(
                "https://www.facebook.com/v15.0/dialog/oauth",
                "https://graph.facebook.com/v15.0/oauth/access_token",
                null,
                null
        );
    }
}
