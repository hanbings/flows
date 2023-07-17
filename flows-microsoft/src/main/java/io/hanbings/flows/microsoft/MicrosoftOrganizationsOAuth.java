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

package io.hanbings.flows.microsoft;

import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class MicrosoftOrganizationsOAuth extends MicrosoftOAuth {
    private MicrosoftOrganizationsOAuth() {
        super(null, null, null, null, null, null, null);
    }

    public MicrosoftOrganizationsOAuth(String client, String secret, String redirect) {
        super(
                "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/organizations/oauth2/v2.0/token",
                client,
                secret,
                redirect,
                List.of(),
                Map.of()
        );
    }

    public MicrosoftOrganizationsOAuth(String client, String secret, String redirect,
                                       List<String> scopes, Map<String, String> params) {
        super(
                "https://login.microsoftonline.com/organizations/oauth2/v2.0/authorize",
                "https://login.microsoftonline.com/organizations/oauth2/v2.0/token",
                client,
                secret,
                redirect,
                scopes,
                params
        );
    }

    @Override
    public String refreshment() {
        return "https://login.microsoftonline.com/organizations/oauth2/v2.0/token";
    }

    @Override
    public String revocation() {
        return "https://login.microsoftonline.com/organizations/oauth2/v2.0/logout";
    }
}