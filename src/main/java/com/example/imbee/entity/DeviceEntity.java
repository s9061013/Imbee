package com.example.imbee.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity
@Table(name = "device", schema = "public", catalog = "imbee")
public class DeviceEntity {
    @Id
    @Column(name = "identifier", nullable = false)
    private String identifier;

    @Basic
    @Column(name = "deliver_at", nullable = false)
    private String deliverAt;
}
