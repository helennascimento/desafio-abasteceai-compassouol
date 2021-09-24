package beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import pages.BasePage;

public class Evidence extends BasePage {

	public static void geradorEvidencia(WebDriver driver, String titulo) throws DocumentException, MalformedURLException, IOException {

		// Capturar screenshot
		byte[] input = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Document documento = new Document();
		String saida = "src/test/resources/Evidencia/ " + titulo + timeEvidence() + ".pdf";
		FileOutputStream fos = new FileOutputStream(saida);

		PdfWriter writer = PdfWriter.getInstance(documento, fos);

		writer.open();
		documento.open();

		Image imagem = Image.getInstance(input);

		imagem.scaleToFit(PageSize.A4.getWidth() / 1, PageSize.A4.getHeight() / 1);

		documento.add(imagem);
		documento.add(new Paragraph(titulo));

		documento.close();
		writer.close();
	}

	public static String timeEvidence() {
		return new SimpleDateFormat("yyyy.MM.dd HH-mm-ss").format(new Date());
	}

	public void evidenciaPorPagina(String titulo) throws DocumentException {
		try {
			geradorEvidencia(driver, titulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
