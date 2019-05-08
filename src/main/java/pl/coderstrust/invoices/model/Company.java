package pl.coderstrust.invoices.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Dane firmy", description = "Company X")
public class Company {

  private final String name;
  private final String taxIdentificationNumber;
  private final String street;
  private final String postalCode;
  private final String town;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  private Company(@JsonProperty("name") String name,
      @JsonProperty("taxIdentificationNumber") String taxIdentificationNumber,
      @JsonProperty("street") String street,
      @JsonProperty("postalCode") String postalCode,
      @JsonProperty("town") String town) {
    this.name = name;
    this.taxIdentificationNumber = taxIdentificationNumber;
    this.street = street;
    this.postalCode = postalCode;
    this.town = town;
  }

  public Company(Company company) {
    this(company.getName(),
        company.getTaxIdentificationNumber(),
        company.getStreet(),
        company.postalCode,
        company.getTown());
  }

  public static class CompanyBuilder {

    private String name;
    private String taxIdentificationNumber;
    private String street;
    private String postalCode;
    private String town;

    public CompanyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public CompanyBuilder taxIdentificationNumber(String taxIdentificationNumber) {
      this.taxIdentificationNumber = taxIdentificationNumber;
      return this;
    }

    public CompanyBuilder street(String street) {
      this.street = street;
      return this;
    }

    public CompanyBuilder postalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public CompanyBuilder town(String town) {
      this.town = town;
      return this;
    }

    public Company build() {
      return new Company(name, taxIdentificationNumber, street, postalCode, town);
    }
  }

  public static CompanyBuilder builder() {
    return new CompanyBuilder();
  }

  @ApiModelProperty(value = "Nazwa firmy", example = "Company X")
  public String getName() {
    return name;
  }
  @ApiModelProperty(value = "Numer NIP", example = "555-444-12-32")
  public String getTaxIdentificationNumber() {
    return taxIdentificationNumber;
  }

  @ApiModelProperty(value = "Ulica", example = "Niepodległosci 23")
  public String getStreet() {
    return street;
  }

  @ApiModelProperty(value = "Kod pocztowy", example = "45-789")
  public String getPostalCode() {
    return postalCode;
  }

  @ApiModelProperty(value = "Miasto", example = "Kraków")
  public String getTown() {
    return town;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Company company = (Company) o;

    if (name != null ? !name.equals(company.name) : company.name != null) {
      return false;
    }
    if (taxIdentificationNumber != null ? !taxIdentificationNumber
        .equals(company.taxIdentificationNumber) : company.taxIdentificationNumber != null) {
      return false;
    }
    if (street != null ? !street.equals(company.street) : company.street != null) {
      return false;
    }
    if (postalCode != null ? !postalCode.equals(company.postalCode) : company.postalCode != null) {
      return false;
    }
    return town != null ? town.equals(company.town) : company.town == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result =
        31 * result + (taxIdentificationNumber != null ? taxIdentificationNumber.hashCode() : 0);
    result = 31 * result + (street != null ? street.hashCode() : 0);
    result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
    result = 31 * result + (town != null ? town.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Company{"
        + "name='" + name + '\''
        + ", taxIdentificationNumber='" + taxIdentificationNumber + '\''
        + ", street='" + street + '\''
        + ", postalCode='" + postalCode + '\''
        + ", town='" + town + '\''
        + '}';
  }
}
