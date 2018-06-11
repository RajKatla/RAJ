package com.identitye2e.fs.pojo;

public class VehicleRegistrationDetails {

    private String registrationNumber;
    private String make;
    private String colour;

    private VehicleRegistrationDetails(final VehicleDetailsBuilder vehicleDetailsBuilder) {
        this.registrationNumber = vehicleDetailsBuilder.registrationNumber;
        this.make = vehicleDetailsBuilder.make;
        this.colour = vehicleDetailsBuilder.colour;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getColour() {
        return colour;
    }

    public static class VehicleDetailsBuilder {
        private String registrationNumber;
        private String make;
        private String colour;

        public VehicleDetailsBuilder withRegistrationNumber(final String registrationNumber) {
            this.registrationNumber = registrationNumber;
            return this;
        }

        public VehicleDetailsBuilder withMake(final String make) {
            this.make = make;
            return this;
        }

        public VehicleDetailsBuilder withColour(final String colour) {
            this.colour = colour;
            return this;
        }

        public VehicleRegistrationDetails build() {
            return new VehicleRegistrationDetails(this);
        }

    }

    @Override
    public String toString() {
        return "VehicleRegistrationDetails{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", make='" + make + '\'' +
                ", colour='" + colour + '\'' +
                '}';
    }
}
