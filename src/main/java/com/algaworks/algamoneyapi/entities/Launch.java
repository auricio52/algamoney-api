package com.algaworks.algamoneyapi.entities;

import com.algaworks.algamoneyapi.entities.enums.LaunchType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "launch")
public class Launch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "release_value")
    private BigDecimal value;
    private String note;
    @Enumerated(EnumType.STRING)
    @Column(name = "release_type")
    private LaunchType type;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LaunchType getType() {
        return type;
    }

    public void setType(LaunchType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Launch launch = (Launch) o;

        if (id != null ? !id.equals(launch.id) : launch.id != null) return false;
        if (description != null ? !description.equals(launch.description) : launch.description != null) return false;
        if (expirationDate != null ? !expirationDate.equals(launch.expirationDate) : launch.expirationDate != null)
            return false;
        if (paymentDate != null ? !paymentDate.equals(launch.paymentDate) : launch.paymentDate != null) return false;
        if (value != null ? !value.equals(launch.value) : launch.value != null) return false;
        if (note != null ? !note.equals(launch.note) : launch.note != null) return false;
        if (type != launch.type) return false;
        if (category != null ? !category.equals(launch.category) : launch.category != null) return false;
        return person != null ? person.equals(launch.person) : launch.person == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (expirationDate != null ? expirationDate.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (person != null ? person.hashCode() : 0);
        return result;
    }
}
