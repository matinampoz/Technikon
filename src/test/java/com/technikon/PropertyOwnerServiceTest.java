package com.technikon;

import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PropertyOwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOwners() {
        List<PropertyOwner> owners = Arrays.asList(
                PropertyOwner.builder()
                        .vatNumber(123456789L)
                        .name("Elon")
                        .surname("Musk")
                        .address("3500 Deer Creek Road, Palo Alto, CA")
                        .phoneNumber(1234567890L)
                        .email("elonmusk@example.com")
                        .username("elonmusk")
                        .password("password123")
                        .build(),
                PropertyOwner.builder()
                        .vatNumber(987654321L)
                        .name("Jeff")
                        .surname("Bezos")
                        .address("410 Terry Ave N, Seattle, WA")
                        .phoneNumber(9876543210L)
                        .email("jeffbezos@example.com")
                        .username("jeffbezos")
                        .password("password321")
                        .build()
        );

        when(ownerRepository.findAll()).thenReturn(owners);

        List<PropertyOwner> result = ownerService.getAllOwners();
        System.out.println(result);
        assertEquals(2, result.size());

        verify(ownerRepository, times(1)).findAll();

    }

    @Test
    public void testSearchOwnerById(){
        PropertyOwner propertyOwner = PropertyOwner.builder()
                .vatNumber(987654321L)
                .name("Jeff")
                .surname("Bezos")
                .address("410 Terry Ave N, Seattle, WA")
                .phoneNumber(9876543210L)
                .email("jeffbezos@example.com")
                .username("jeffbezos")
                .password("password321")
                .build();

        when(ownerRepository.findById(1L)).thenReturn(Optional.ofNullable(propertyOwner));
        Optional<PropertyOwner> result = ownerService.searchOwnerById(String.valueOf(1L));
        assertEquals(987654321L, result.get().getVatNumber());
        assertEquals("Jeff", result.get().getName());
        assertEquals("Bezos", result.get().getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.get().getAddress());
        assertEquals(9876543210L, result.get().getPhoneNumber());
        assertEquals("jeffbezos@example.com", result.get().getEmail());
        assertEquals("jeffbezos", result.get().getUsername());
        assertEquals("password321", result.get().getPassword());

        verify(ownerRepository, times(1)).findById(1L);

    }

    @Test
    public void testSearchOwnerByEmail(){
        PropertyOwner propertyOwner = PropertyOwner.builder()
                .vatNumber(987654321L)
                .name("Jeff")
                .surname("Bezos")
                .address("410 Terry Ave N, Seattle, WA")
                .phoneNumber(9876543210L)
                .email("jeffbezos@example.com")
                .username("jeffbezos")
                .password("password321")
                .build();

        Optional<PropertyOwner> result = Optional.ofNullable(ownerService.searchOwnerByEmail("jeffbezos@example.com"));
        assertEquals(987654321L, result.get().getVatNumber());
        assertEquals("Jeff", result.get().getName());
        assertEquals("Bezos", result.get().getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.get().getAddress());
        assertEquals(9876543210L, result.get().getPhoneNumber());
        assertEquals("jeffbezos", result.get().getUsername());
        assertEquals("password321", result.get().getPassword());
    }

    @Test
    public void testSearchOwnerByVatNumber(){
        PropertyOwner propertyOwner = PropertyOwner.builder()
                .vatNumber(987654321L)
                .name("Jeff")
                .surname("Bezos")
                .address("410 Terry Ave N, Seattle, WA")
                .phoneNumber(9876543210L)
                .email("jeffbezos@example.com")
                .username("jeffbezos")
                .password("password321")
                .build();

        Optional<PropertyOwner> result = Optional.ofNullable(ownerService.searchOwnerByVat("987654321"));
        assertEquals("Jeff", result.get().getName());
        assertEquals("Bezos", result.get().getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.get().getAddress());
        assertEquals(9876543210L, result.get().getPhoneNumber());
        assertEquals("jeffbezos@example.com", result.get().getEmail());
        assertEquals("jeffbezos", result.get().getUsername());
        assertEquals("password321", result.get().getPassword());
    }

    /*@Test
    public void testCreateOwner() {
        PropertyOwner propertyOwner = PropertyOwner.builder()
                .vatNumber(987654321L)
                .name("Jeff")
                .surname("Bezos")
                .address("410 Terry Ave N, Seattle, WA")
                .phoneNumber(9876543210L)
                .email("jeffbezos@example.com")
                .username("jeffbezos")
                .password("password321")
                .build();

        when(ownerRepository.save(any(PropertyOwner.class))).thenReturn(Optional.of(propertyOwner));

        Optional<PropertyOwner> result = ownerService.createOwner(propertyOwner);
        assertEquals(987654321L, result.get().getVatNumber());
        assertEquals("Jeff", result.get().getName());
        assertEquals("Bezos", result.get().getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.get().getAddress());
        assertEquals(9876543210L, result.get().getPhoneNumber());
        assertEquals("jeffbezos@example.com", result.get().getEmail());
        assertEquals("jeffbezos", result.get().getUsername());
        assertEquals("password321", result.get().getPassword());

        verify(ownerRepository, times(1)).save(propertyOwner);
    }*/

    @Test
    public void testSaveOwner() {
        PropertyOwner propertyOwner = PropertyOwner.builder()
                .vatNumber(123456789L)
                .name("Bill")
                .surname("Gates")
                .address("1 Microsoft Way, Redmond, WA")
                .phoneNumber(1234567890L)
                .email("billgates@example.com")
                .username("billgates")
                .password("password123")
                .build();

        when(ownerRepository.save(any(PropertyOwner.class))).thenReturn(Optional.of(propertyOwner));

        Long result = ownerService.saveOwner(propertyOwner);
        Optional<PropertyOwner> ownerResult = ownerService.searchOwnerById(String.valueOf(result));
        assertTrue(ownerService.searchOwnerById(String.valueOf(ownerResult)).isPresent());
        assertEquals(123456789L, ownerResult.get().getVatNumber());
        assertEquals("Bill", ownerResult.get().getName());
        assertEquals("Gates", ownerResult.get().getSurname());
        assertEquals("1 Microsoft Way, Redmond, WA", ownerResult.get().getAddress());
        assertEquals(1234567890L, ownerResult.get().getPhoneNumber());
        assertEquals("billgates@example.com", ownerResult.get().getEmail());
        assertEquals("billgates", ownerResult.get().getUsername());
        assertEquals("password123", ownerResult.get().getPassword());

        verify(ownerRepository, times(1)).save(propertyOwner);
    }

    @Test
    public void testDeleteOwner() {
        Long ownerId = 1L;

        when(ownerRepository.deleteById(ownerId)).thenReturn(true);

        boolean result = ownerService.deleteOwner(String.valueOf(ownerId));
        assertTrue(result);

        verify(ownerRepository, times(1)).deleteById(ownerId);
    }

}
