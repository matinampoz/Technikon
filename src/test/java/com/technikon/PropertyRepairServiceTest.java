package com.technikon;

public class PropertyRepairServiceTest {

//    @Mock
//    private PropertyRepairRepository propertyRepairRepository;
//
//    @InjectMocks
//    private PropertyRepairService propertyRepairService;
//
//    private PropertyOwner owner;
//    private Property property;
//    private PropertyRepair repair;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        owner = new PropertyOwner(1L, 123456789L, "John", "Doe", "123 Elm Street", 5551234L, "john.doe@example.com", "johnny", "securepassword", null);
//        property = new Property(1L, "A001", "123 Elm Street", 1990, PropertyType.MAISONETTE, owner);
//        repair = PropertyRepair.builder()
//                .repairId(1L)
//                .owner(owner)
//                .property(property)
//                .typeOfRepair(RepairType.FRAMES)
//                .shortDescription("Fixing window frames")
//                .submissionDate(LocalDateTime.now())
//                .workDescription("Work description")
//                .proposedStartDate(LocalDateTime.now().plusDays(1))
//                .proposedEndDate(LocalDateTime.now().plusDays(2))
//                .proposedCost(1000.0)
//                .status(RepairStatus.PENDING)
//                .build();
//    }
//
//    @Test
//    public void testCreatePropertyRepair() {
//        PropertyRepair newRepair = propertyRepairService.createPropertyRepair(owner, property, RepairType.FRAMES,
//                "Fixing window frames", "Work description", LocalDateTime.now().plusDays(1),
//                LocalDateTime.now().plusDays(2), 1000.0);
//
//        assertNotNull(newRepair);
//        assertEquals(owner, newRepair.getOwner());
//        assertEquals(property, newRepair.getProperty());
//        assertEquals(RepairType.FRAMES, newRepair.getTypeOfRepair());
//        assertEquals("Fixing window frames", newRepair.getShortDescription());
//    }
//
//    @Test
//    public void testSavePropertyRepair() {
//        when(propertyRepairRepository.save(repair)).thenReturn(Optional.of(repair));
//        Long repairId = propertyRepairService.savePropertyRepair(repair);
//        assertNotNull(repairId);
//        assertEquals(repair.getRepairId(), repairId);
//    }
//
//    @Test
//    public void testGetPropertyRepairs() {
//        when(propertyRepairRepository.findAll()).thenReturn(Arrays.asList(repair));
//        List<PropertyRepair> repairs = propertyRepairService.getPropertyRepairs();
//        assertNotNull(repairs);
//        assertFalse(repairs.isEmpty());
//        assertEquals(1, repairs.size());
//    }
//
//    @Test
//    public void testUpdatePropertyRepair() {
//        when(propertyRepairRepository.save(repair)).thenReturn(Optional.of(repair));
//        PropertyRepair updatedRepair = propertyRepairService.updatePropertyRepair(repair);
//        assertNotNull(updatedRepair);
//        assertEquals(repair.getRepairId(), updatedRepair.getRepairId());
//    }
//
//    @Test
//    public void testDeletePropertyRepair() {
//        doNothing().when(propertyRepairRepository).deleteById(repair.getRepairId());
//        propertyRepairService.deletePropertyRepair(repair.getRepairId());
//        verify(propertyRepairRepository, times(1)).deleteById(repair.getRepairId());
//    }
//
//    @Test
//    public void testSearchPropertyRepairsByDateRange() {
//        when(propertyRepairRepository.findBySubmissionDateBetween(any(LocalDateTime.class), any(LocalDateTime.class)))
//                .thenReturn(Arrays.asList(repair));
//
//        List<PropertyRepair> repairs = propertyRepairService.searchPropertyRepairsByDateRange(
//                LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
//        assertNotNull(repairs);
//        assertFalse(repairs.isEmpty());
//        assertEquals(1, repairs.size());
//    }
//
//    @Test
//    public void testSearchPropertyRepairsByOwnerId() {
//        when(propertyRepairRepository.findByOwner_Id(owner.getOwnerId())).thenReturn(Arrays.asList(repair));
//        List<PropertyRepair> repairs = propertyRepairService.searchPropertyRepairsByOwnerId(owner.getOwnerId());
//        assertNotNull(repairs);
//        assertFalse(repairs.isEmpty());
//        assertEquals(1, repairs.size());
//    }
}
