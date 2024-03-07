package com.weCare.modals;

/*
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "slots")
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String  slotId;
	
	private String slotDescription;
	
	private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private LocalDate slotDate;
	
    @Enumerated(EnumType.STRING)
	private SlotPeriod slotPeriod;
	
	@Enumerated(EnumType.STRING)
	private SlotStatus slotStatus;

	@JsonIgnore
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
	
	
	@ManyToMany(mappedBy = "slots")
	private List<Doctor> doctors;
	
	@OneToOne
	@JoinColumn(name = "appointment_id")
	private Appointment appointment;
}
*/
