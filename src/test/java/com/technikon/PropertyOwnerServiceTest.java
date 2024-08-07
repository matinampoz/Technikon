package com.technikon;

import com.technikon.exceptions.OwnerException;
import com.technikon.models.PropertyOwner;
import com.technikon.repositories.OwnerRepository;
import com.technikon.services.OwnerService;
import com.technikon.services.OwnerServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PropertyOwnerServiceTest {



    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceImpl ownerServiceImpl;

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

        List<PropertyOwner> result = ownerServiceImpl.getAllOwners();
        System.out.println(result);
        assertEquals(2, result.size());

        verify(ownerRepository, times(1)).findAll();

    }

    @Test
    public void testSearchOwnerById() throws OwnerException {
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
        Optional<PropertyOwner> result = Optional.ofNullable(ownerServiceImpl.searchOwnerById(String.valueOf(1L)));
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
    public void testSearchOwnerByEmail() throws OwnerException {
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

        Optional<PropertyOwner> result = Optional.ofNullable(ownerServiceImpl.searchOwnerByEmail("jeffbezos@example.com"));
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

        Optional<PropertyOwner> result = Optional.ofNullable(ownerServiceImpl.searchOwnerByVat("987654321"));
        assertEquals("Jeff", result.get().getName());
        assertEquals("Bezos", result.get().getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.get().getAddress());
        assertEquals(9876543210L, result.get().getPhoneNumber());
        assertEquals("jeffbezos@example.com", result.get().getEmail());
        assertEquals("jeffbezos", result.get().getUsername());
        assertEquals("password321", result.get().getPassword());
    }

    @Test
    public void testCreateOwner() {

        PropertyOwner result = ownerServiceImpl.createOwner(987654321L, "Jeff", "Bezos",
                "410 Terry Ave N, Seattle, WA",
                9876543210L, "jeffbezos@example.com",
                "jeffbezos", "password321");
        assertEquals(987654321L, result.getVatNumber());
        assertEquals("Jeff", result.getName());
        assertEquals("Bezos", result.getSurname());
        assertEquals("410 Terry Ave N, Seattle, WA", result.getAddress());
        assertEquals(9876543210L, result.getPhoneNumber());
        assertEquals("jeffbezos@example.com", result.getEmail());
        assertEquals("jeffbezos", result.getUsername());
        assertEquals("password321", result.getPassword());

    }

    /*@Test
    public void testSaveOwner() throws OwnerException {
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

        Long result = ownerServiceImpl.saveOwner(propertyOwner);
        Optional<PropertyOwner> ownerResult = Optional.ofNullable(ownerServiceImpl.searchOwnerById(String.valueOf(result)));
        assertTrue(ownerServiceImpl.searchOwnerById(String.valueOf(ownerResult)).isPresent());
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
*/
    @Test
    public void testDeleteOwner() throws OwnerException {
        Long ownerId = 1L;

        when(ownerRepository.deleteById(ownerId)).thenReturn(true);

        boolean result = ownerServiceImpl.deleteOwner(String.valueOf(ownerId));
        assertTrue(result);

        verify(ownerRepository, times(1)).deleteById(ownerId);
    }

}
