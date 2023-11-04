package med.voll.api.jpas;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dtos.AddressData;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String logradouro;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.logradouro = address.logradouro();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.city = address.city();
        this.uf = address.uf();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void updateInformation(AddressData data) {
        if (data.logradouro() != null) {
            this.logradouro = data.logradouro();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
    }
}
