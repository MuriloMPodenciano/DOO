package ifsp.doo.atas.domain.usecases.ata;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ifsp.doo.atas.domain.DTO.informe.InformeGetResponseDTO;
import ifsp.doo.atas.domain.model.AtaState;
import org.springframework.beans.factory.annotation.Autowired;

import ifsp.doo.atas.domain.DTO.ata.AtaGetPersistDTO;
import ifsp.doo.atas.domain.DTO.ata.AtaGetResponseDTO;
import ifsp.doo.atas.domain.repository.AtaRepository;

public class BuscarAtaUseCase {
    @Autowired
    private AtaRepository repository;

    public List<AtaGetResponseDTO> getAll() {
        return repository.findAll()
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByPalavra(String palavra) {
        if (palavra == null)
            throw new IllegalArgumentException("palavra chave is empty in palavra chave mode");
    
        return repository.findAllByPalavraChave(palavra)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByDate(LocalDateTime dataInicio, LocalDateTime dataFim) {
        if (!isDateRangeCorrect(dataInicio, dataFim))
            throw new IllegalArgumentException("date period is invalid in date period mode");

        return repository.findAllByRange(dataInicio, dataFim)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    public List<AtaGetResponseDTO> getAllByGrupo(Long id) {
        if (id == null)
            throw new IllegalArgumentException("grupo id is empty in grupo id mode");

        return repository.findAllByGrupoId(id)
            .stream()
            .map(AtaGetResponseDTO::new)
            .collect(Collectors.toList());
    }

    private boolean isDateRangeCorrect(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return dataInicio != null && dataFim != null
            && dataInicio.compareTo(dataFim) <= 0;
    }

    public AtaGetResponseDTO get(Long id) {
        return new AtaGetResponseDTO(repository.getReferenceById(id));
    }

    public void toPDF(Long id) {
        AtaGetResponseDTO ata = get(id);
        int qtdCapitulos = 1;

        Font fonteTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 24, BaseColor.BLUE);
        Font fonteSubTitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK);
        Font fonteParagrafo = FontFactory.getFont(FontFactory.TIMES_ROMAN, Font.DEFAULTSIZE, BaseColor.BLACK);
        Document pdf = new Document();
        try{
            PdfWriter writer = PdfWriter.getInstance(pdf, new FileOutputStream(ata.titulo()));
            pdf.open();

            pdf.addAuthor(ata.nomeRedator());
            pdf.addCreator("MinutesMeet");
            pdf.addCreationDate();
            pdf.addTitle(ata.titulo());
            pdf.addSubject(ata.descricao());

            Paragraph titulo = new Paragraph(ata.titulo(), fonteTitulo);
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            pdf.add(titulo);
            pdf.newPage();

            Chapter inicioFim = new Chapter(new Paragraph("Inicio e término", fonteSubTitulo), qtdCapitulos);
            pdf.add(inicioFim);
            qtdCapitulos++;
            com.itextpdf.text.List datas = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
            datas.add("Inicio: " + ata.dataInicio());
            if (ata.horaEncerramento() == null){
                datas.add(new Paragraph("Fim previsto: " + ata.fimPrevisto(), fonteParagrafo));
            }else{
                datas.add(new Paragraph("Término: " + ata.horaEncerramento(), fonteParagrafo));
            }
            pdf.add(datas);
            pdf.add(new Paragraph(""));

            Chapter descricao = new Chapter(new Paragraph("Descrição", fonteSubTitulo), qtdCapitulos);
            pdf.add(descricao);
            qtdCapitulos++;
            pdf.add(new Paragraph(ata.descricao()));
            pdf.add(new Paragraph("Redator: " + ata.nomeRedator(), fonteParagrafo));
            pdf.newPage();

            Chapter abertura = new Chapter(new Paragraph("Abertura", fonteSubTitulo), qtdCapitulos);
            pdf.add(abertura);
            qtdCapitulos++;
            pdf.add(new Paragraph(ata.textoAbertura()));
            if(ata.estado().equals(AtaState.FINISHED)){
                Chapter encerramento = new Chapter(new Paragraph("Encerramento", fonteSubTitulo), qtdCapitulos);
                pdf.add(encerramento);
                qtdCapitulos++;
                pdf.add(new Paragraph(ata.textoEncerramento(), fonteParagrafo));
            }
            pdf.newPage();

            List<InformeGetResponseDTO> informes = ata.informes();
            Chapter capituloInforme = new Chapter(new Paragraph("Informe", fonteSubTitulo), qtdCapitulos);
            pdf.add(capituloInforme);
            qtdCapitulos++;
            PdfPTable tabelaInforme = new PdfPTable(3);
            tabelaInforme.setWidthPercentage(80);
            PdfPCell celulaTituloInforme = new PdfPCell(new Phrase("Informe"));
            celulaTituloInforme.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaTituloInforme.setVerticalAlignment(Element.ALIGN_CENTER);
            tabelaInforme.addCell(celulaTituloInforme);
            PdfPCell celulaTituloPessoa = new PdfPCell(new Phrase("Pessoa"));
            celulaTituloPessoa.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaTituloPessoa.setVerticalAlignment(Element.ALIGN_CENTER);
            tabelaInforme.addCell(celulaTituloPessoa);
            PdfPCell celulaTituloInformado = new PdfPCell(new Phrase("Informado"));
            celulaTituloInformado.setHorizontalAlignment(Element.ALIGN_CENTER);
            celulaTituloInformado.setVerticalAlignment(Element.ALIGN_CENTER);
            tabelaInforme.addCell(celulaTituloInformado);
            for(InformeGetResponseDTO informe : informes){
                PdfPCell celulaInforme = new PdfPCell(new Phrase(informe.informe()));
                celulaInforme.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaInforme.setVerticalAlignment(Element.ALIGN_CENTER);
                tabelaInforme.addCell(celulaInforme);
                PdfPCell celulaPessoa = new PdfPCell(new Phrase(informe.pessoa().nome()));
                celulaPessoa.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaPessoa.setVerticalAlignment(Element.ALIGN_CENTER);
                tabelaInforme.addCell(celulaPessoa);
                PdfPCell celulaInformado = new PdfPCell(new Phrase("Informado"));
                celulaInformado.setHorizontalAlignment(Element.ALIGN_CENTER);
                celulaInformado.setVerticalAlignment(Element.ALIGN_CENTER);
                tabelaInforme.addCell(celulaInformado);
            }
            pdf.newPage();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
