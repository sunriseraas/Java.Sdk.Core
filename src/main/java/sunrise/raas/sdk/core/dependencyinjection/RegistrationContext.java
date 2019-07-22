package sunrise.raas.sdk.core.dependencyinjection;

public interface RegistrationContext<TRegistrationContext extends RegistrationContext<TRegistrationContext>> {
    <TService, TImplementation extends TService> TRegistrationContext register(Class<TService> serviceClass,
                                                                               Class<TImplementation> implementationClass);
}
