package com.farm.collector.management.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "farm")
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder")
public class Farm extends AuditAwareBaseEntity {

    private String name;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL)
    private List<Field> fields;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Farm that = (Farm) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
