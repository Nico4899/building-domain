package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "room")
public class Room {

  @Column(name = "floor", nullable = false, updatable = false, columnDefinition = "TEXT")
  private int floor;

  @Column(name = "room_name", nullable = false, updatable = false, columnDefinition = "TEXT")
  private String roomName;

  @Column(name = "room_number", nullable = false, updatable = false, columnDefinition = "TEXT")
  private String roomNumber;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence")
  @SequenceGenerator(name = "room_sequence", allocationSize = 1)
  @GenericGenerator(
      name = "room_sequence",
      strategy =
          "edu/kit/tm/cm/smartcampus/building/infrastructure/database/PrefixSequenceGenerator.java",
      parameters = {
        @Parameter(name = PrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "r-")
      })
  @Column(
      name = "identification_number",
      nullable = false,
      updatable = false,
      columnDefinition = "TEXT")
  private String identificationNumber;

  @Column(
      name = "parent_identification_number",
      nullable = false,
      updatable = false,
      columnDefinition = "TEXT")
  private String parentIdentificationNumber;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geographical_location")
  private GeographicalLocation geographicalLocation;

  @Column(name = "room_type", nullable = false, updatable = false, columnDefinition = "TEXT")
  private RoomType roomType;
}
