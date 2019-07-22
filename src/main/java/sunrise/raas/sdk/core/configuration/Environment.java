package sunrise.raas.sdk.core.configuration;

import sunrise.raas.sdk.core.helpers.Equatable;

public enum Environment implements Equatable<Environment> {
    Production("https://prod.sunrisetechnology.com"),
    NonProduction("https://nonprod.sunrisetechnology.com");

    private final String baseUrl;

    Environment(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public boolean isEqualTo(Environment other) {
        return other != null &&
                baseUrl.equalsIgnoreCase(other.baseUrl);
    }
}
