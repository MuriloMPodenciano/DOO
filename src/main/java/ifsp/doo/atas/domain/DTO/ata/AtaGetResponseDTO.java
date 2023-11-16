package ifsp.doo.atas.domain.DTO.ata;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public record AtaGetResponseDTO() {

    public AtaGetResponseDTO(AtaGetPersistDTO save) {
    }
    
}
