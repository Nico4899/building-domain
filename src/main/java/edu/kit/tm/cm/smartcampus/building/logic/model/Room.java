package edu.kit.tm.cm.smartcampus.building.logic.model;

import edu.kit.tm.cm.smartcampus.building.infrastructure.database.generator.PrefixSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "room")
public class Room {

  @Column(name = "room_name")
  private String roomName;

  @Column(name = "room_number")
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
  @Column(name = "identification_number")
  private String identificationNumber;

  @Column(name = "parent_identification_number")
  private String parentIdentificationNumber;

  private int floor;

  @Column(name = "room_type")
  private RoomType roomType;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.PERSIST)
  @JoinColumn(name = "geographical_location")
  private GeographicalLocation geographicalLocation;
}
