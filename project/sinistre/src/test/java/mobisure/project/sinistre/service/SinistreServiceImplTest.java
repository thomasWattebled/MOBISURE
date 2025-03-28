package mobisure.project.sinistre.service;

import mobisure.project.sinistre.dto.SinistreDto;
import mobisure.project.sinistre.entity.Sinistre;
import mobisure.project.sinistre.repository.SinistreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SinistreServiceImplTest {

    @Mock
    private SinistreRepository sinistreRepo;

    @InjectMocks
    private SinistreServiceImpl sinistreService;

    @Test
    void testConvertToEntity() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Accident", new Date());
        Sinistre sinistre = sinistreService.convertToEntity(sinistreDto);

        assertNotNull(sinistre);
        assertEquals(sinistreDto.getContractId(), sinistre.getContractId());
        assertEquals(sinistreDto.getUserId(), sinistre.getUserId());
        assertEquals(sinistreDto.getCategory(), sinistre.getCategory());
        assertEquals(sinistreDto.getDate(), sinistre.getDate());
    }

    @Test
    void testConvertToDto() {
        Sinistre sinistre = new Sinistre(123L, 456L, "Accident", new Date());
        sinistre.setId(1L);
        SinistreDto sinistreDto = sinistreService.convertToDto(sinistre);

        assertNotNull(sinistreDto);
        assertEquals(sinistre.getContractId(), sinistreDto.getContractId());
        assertEquals(sinistre.getUserId(), sinistreDto.getUserId());
        assertEquals(sinistre.getCategory(), sinistreDto.getCategory());
        assertEquals(sinistre.getDate(), sinistreDto.getDate());
        assertEquals(sinistre.getId(), sinistreDto.getId());
    }

    @Test
    void testGetSinistreById() {
        Long id = 1L;
        Sinistre sinistre = new Sinistre(123L, 456L, "Accident", new Date());
        sinistre.setId(id);

        when(sinistreRepo.findById(id)).thenReturn(Optional.of(sinistre));

        SinistreDto sinistreDto = sinistreService.getSinistreById(id);

        assertNotNull(sinistreDto);
        assertEquals(id, sinistreDto.getId());
        assertEquals(sinistre.getContractId(), sinistreDto.getContractId());
        assertEquals(sinistre.getUserId(), sinistreDto.getUserId());
        assertEquals(sinistre.getCategory(), sinistreDto.getCategory());
        assertEquals(sinistre.getDate(), sinistreDto.getDate());
    }

    @Test
    void testGetSinistreByIdNotFound() {
        Long id = 1L;

        when(sinistreRepo.findById(id)).thenReturn(Optional.empty());

        SinistreDto sinistreDto = sinistreService.getSinistreById(id);

        assertNull(sinistreDto);
    }

    @Test
    void testGetSinitreByUserId() {
        Long userId = 456L;
        Sinistre sinistre1 = new Sinistre(123L, userId, "Accident", new Date());
        Sinistre sinistre2 = new Sinistre(124L, userId, "Fire", new Date());

        when(sinistreRepo.findByUserId(userId)).thenReturn(Arrays.asList(sinistre1, sinistre2));

        List<SinistreDto> sinistreDtos = sinistreService.getSinitreByUserId(userId);

        assertNotNull(sinistreDtos);
        assertEquals(2, sinistreDtos.size());
        assertEquals(sinistre1.getContractId(), sinistreDtos.get(0).getContractId());
        assertEquals(sinistre2.getContractId(), sinistreDtos.get(1).getContractId());
    }

    @Test
    void testGetSinitreByContractId() {
        Long contractId = 123L;
        Sinistre sinistre1 = new Sinistre(contractId, 456L, "Accident", new Date());
        Sinistre sinistre2 = new Sinistre(contractId, 789L, "Fire", new Date());

        when(sinistreRepo.findByContractId(contractId)).thenReturn(Arrays.asList(sinistre1, sinistre2));

        List<SinistreDto> sinistreDtos = sinistreService.getSinitreByContractId(contractId);

        assertNotNull(sinistreDtos);
        assertEquals(2, sinistreDtos.size());
        assertEquals(sinistre1.getCategory(), sinistreDtos.get(0).getCategory());
        assertEquals(sinistre2.getCategory(), sinistreDtos.get(1).getCategory());
    }

    @Test
    void testSaveSinistre() {
        SinistreDto sinistreDto = new SinistreDto(123L, 456L, "Accident", new Date());
        Sinistre sinistreEntity = new Sinistre(sinistreDto.getContractId(), sinistreDto.getUserId(),
                sinistreDto.getCategory(), sinistreDto.getDate());

        when(sinistreRepo.save(any(Sinistre.class))).thenReturn(sinistreEntity);

        Sinistre savedSinistre = sinistreService.saveSinistre(sinistreDto);

        assertNotNull(savedSinistre);
        assertEquals(sinistreDto.getContractId(), savedSinistre.getContractId());
        assertEquals(sinistreDto.getUserId(), savedSinistre.getUserId());
        assertEquals(sinistreDto.getCategory(), savedSinistre.getCategory());
        assertEquals(sinistreDto.getDate(), savedSinistre.getDate());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        sinistreService.delete(id);

        verify(sinistreRepo, times(1)).deleteById(id);
    }
}
