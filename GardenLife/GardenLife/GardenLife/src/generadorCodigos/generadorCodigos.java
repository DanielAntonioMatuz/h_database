package generadorCodigos;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class generadorCodigos {

public void generarPDF(String header, String info, String footer, String rutaImagen, String salida, String codigo, String nombre) {
		
		try {
		Document document = new Document(PageSize.A4,36,36,10,10);

		PdfWriter pw = PdfWriter.getInstance(document, new FileOutputStream("./src/codigosBarra/"+codigo+ "_"+ nombre + ".pdf"));
		document.open();
		document.add(getHeader(header));
		Image image = Image.getInstance(rutaImagen);
		image.scaleAbsolute(300, 120);
		image.setAlignment(Element.ALIGN_CENTER);
		document.add(image);
		document.add(getInfo(info));
		document.add(getInfo(" "));
		document.add(getInfo(" "));
		document.add(getInfo(" "));
		document.add(getBarcode(document,pw,codigo));
		document.add(getInfo(" "));
		document.add(getFooter(footer));
		document.close();
		
		try {

            File objetofile = new File ("./src/codigosBarra/"+codigo+ "_"+ nombre + ".pdf");
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);

     }
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

private Paragraph getFooter(String texto) {
	Paragraph p = new Paragraph();
	Chunk c = new Chunk();
	p.setAlignment(Element.ALIGN_RIGHT);
	c.append(texto);
	p.add(c);
	
	return p;
}

private Paragraph getInfo(String texto) {
	Paragraph p = new Paragraph();
	Chunk c = new Chunk();
	p.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
	c.append(texto);
	p.add(c);
	return p;
	
}

private Paragraph getHeader(String texto) {
	Paragraph p = new Paragraph();
	Chunk c = new Chunk();
	p.setAlignment(Element.ALIGN_CENTER);
	c.append(texto);
	p.add(c);
	return p;
}

private Image getBarcode(Document document, PdfWriter pw, String codigo) {
	
	PdfContentByte cimg = pw.getDirectContent();
	Barcode128 code128 = new Barcode128();
	code128.setCode(codigo);
	code128.setCodeType(Barcode128.CODE128);
	code128.setTextAlignment(Element.ALIGN_CENTER);
	
	Image image = code128.createImageWithBarcode(cimg, BaseColor.BLACK, BaseColor.BLACK);
	float scaler = ((document.getPageSize().getWidth() - document.leftMargin()-document.rightMargin()-0)/image.getWidth()*60);
	image.scalePercent(scaler);
	image.setAlignment(Element.ALIGN_CENTER);
	
	return image;
	
}

private String formatearCodigo(String num) {
	NumberFormat form = new DecimalFormat("0000000");
	return form.format((num!=null)? Integer.parseInt(num):0000000);
	
}
}
