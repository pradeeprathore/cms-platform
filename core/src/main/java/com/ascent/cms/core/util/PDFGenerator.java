package com.ascent.cms.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Component
public class PDFGenerator
{
/*
	public String getVisitPdf(VisitVO visitVO, File directory)
	{
		File fileToStore = null;
		try
		{
			fileToStore = prepareFile(directory);
			OutputStream file = new FileOutputStream(fileToStore);
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			String doctorName = "Dr. " + visitVO.getDoctor().getFirstName() + " " + visitVO.getDoctor().getLastName();
			String doctormobile = "Mobile " + visitVO.getDoctor().getMobile();
			String doctorAddress1 = "Address: " + visitVO.getDoctor().getAddress().getAddressLine1() + ","
					+ visitVO.getDoctor().getAddress().getArea();
			String doctorAddress2 = visitVO.getDoctor().getAddress().getCity() + ","
					+ visitVO.getDoctor().getAddress().getZipCode();
			String patientName = "Name: " + visitVO.getMedicalCase().getPatient().getFirstName() + " "
					+ visitVO.getMedicalCase().getPatient().getLastName();
			String visitDate = "Visit Date: "
					+ new SimpleDateFormat("dd-MM-yyyy").format(visitVO.getDate());
			String patientAge = "Age: " + AgeCalculation.getAgeAsString(visitVO.getMedicalCase().getPatient().getDateOfBirth());
			String medicalCase = "Case: " + visitVO.getMedicalCase().getName();

			List<Paragraph> headerData = new ArrayList<Paragraph>();
			headerData.add(createParagraph(doctorName, 15, 0, 0.0f));
			headerData.add(createParagraph(doctormobile, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress1, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress2, 8, 0, 0.0f));
			headerData.add(createParagraph(patientName, 9, 0, 30.0f));
			headerData.add(createParagraph(visitDate, 8, 0, 0.0f));
			headerData.add(createParagraph(patientAge, 8, 0, 0.0f));
			headerData.add(createParagraph(medicalCase, 8, 0, 0.0f));

			List<PdfPTable> bodyData = new ArrayList<PdfPTable>();

			// Inserting examination notes table
			if (StringUtils.isNotBlank(visitVO.getExaminationNote()))
			{
				PdfPCell tableTitle = prepareTableHeader("Examination Notes", 1);
				PdfPCell contents = prepareDocumentNotes(visitVO.getExaminationNote());
				PdfPTable pdfPTable = createTable(1, tableTitle, null, null, contents);
				bodyData.add(pdfPTable);
			}

			// Inserting complain table
			if (CollectionUtils.isNotEmpty(visitVO.getObservedComplains()))
			{
				PdfPCell tableTitle = prepareTableHeader("Complains", 2);

				List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
				columnTitle.add(createColumnTitle("Complain", 10, 4.0f));
				columnTitle.add(createColumnTitle("Notes", 10, 4.0f));

				List<Paragraph> columnvalue = new ArrayList<Paragraph>();
				Set<ObservedComplain> observedComplain = visitVO.getObservedComplains();
				for (ObservedComplain pmTemp : observedComplain)
				{
					columnvalue.add(createParagraph(pmTemp.getComplain().getName(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getNotes(), 9, 0, 0.0f));
				}
				PdfPTable pdfPTable = createTable(2, tableTitle, columnTitle, columnvalue, null);
				bodyData.add(pdfPTable);
			}

			// Inserting Diagnosed Diseas table
			if (CollectionUtils.isNotEmpty(visitVO.getDiagnosedDisease()))
			{
				PdfPCell tableTitle = prepareTableHeader("Diagnosis", 2);

				List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
				columnTitle.add(createColumnTitle("Diagnosis", 10, 4.0f));
				columnTitle.add(createColumnTitle("Notes", 10, 4.0f));

				List<Paragraph> columnvalue = new ArrayList<Paragraph>();
				Set<DiagnosedDisease> diagnosedDisease = visitVO.getDiagnosedDisease();
				for (DiagnosedDisease pmTemp : diagnosedDisease)
				{
					columnvalue.add(createParagraph(pmTemp.getDisease().getName(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getNotes(), 9, 0, 0.0f));
				}

				PdfPTable pdfPTable = createTable(2, tableTitle, columnTitle, columnvalue, null);
				bodyData.add(pdfPTable);
			}

			// Inserting investigation table
			if (CollectionUtils.isNotEmpty(visitVO.getPrescribedDiagnosises()))
			{
				PdfPCell tableTitle = prepareTableHeader("Investigations", 2);

				List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
				columnTitle.add(createColumnTitle("Investigation ", 10, 4.0f));
				columnTitle.add(createColumnTitle("Instructions", 10, 4.0f));
				List<Paragraph> columnvalue = new ArrayList<Paragraph>();
				Set<PrescribedDiagnosis> prescribedDiagnosis = visitVO.getPrescribedDiagnosises();
				for (PrescribedDiagnosis pmTemp : prescribedDiagnosis)
				{
					columnvalue.add(createParagraph(pmTemp.getDiagnosis().getName(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getNotes(), 9, 0, 0.0f));

				}
				PdfPTable pdfPTable = createTable(2, tableTitle, columnTitle, columnvalue, null);
				bodyData.add(pdfPTable);
			}

			// Inserting Prescribed Medicines Table
			if (CollectionUtils.isNotEmpty(visitVO.getPrescribedMedicines()))
			{
				PdfPCell tableTitle = prepareTableHeader("Medicines", 5);

				List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
				columnTitle.add(createColumnTitle("Medicine ", 10, 4.0f));
				columnTitle.add(createColumnTitle("Duration", 10, 4.0f));
				columnTitle.add(createColumnTitle("Frequency ", 10, 4.0f));
				columnTitle.add(createColumnTitle("Instructions", 10, 4.0f));
				columnTitle.add(createColumnTitle("Strength", 10, 4.0f));

				List<Paragraph> columnvalue = new ArrayList<Paragraph>();
				Set<PrescribedMedicine> prescribedMedicine = visitVO.getPrescribedMedicines();
				for (PrescribedMedicine pmTemp : prescribedMedicine)
				{
					columnvalue.add(createParagraph(pmTemp.getDrug().getName(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(String.valueOf(pmTemp.getDuration()), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getFrequency(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getInstruction(), 9, 0, 0.0f));
					columnvalue.add(createParagraph(pmTemp.getDrug().getStrength(), 9, 0, 0.0f));

				}
				PdfPTable pdfPTable = createTable(5, tableTitle, columnTitle, columnvalue, null);
				bodyData.add(pdfPTable);
			}
			prepareDocument(document, headerData, bodyData);
			file.close();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return fileToStore.getAbsolutePath();
	}

	public String getMedicinePrescriptionsPdf(MedicinePrescriptionVO medicinePrescriptionVO, File directory)
	{

		File fileToStore = null;

		try
		{

			fileToStore = prepareFile(directory);
			OutputStream file = new FileOutputStream(fileToStore);
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			String doctorName = "Dr. " + medicinePrescriptionVO.getDoctor().getFirstName() + " "
					+ medicinePrescriptionVO.getDoctor().getLastName();
			String doctormobile = "Mobile " + medicinePrescriptionVO.getDoctor().getMobile();
			String doctorAddress1 = "Address: " + medicinePrescriptionVO.getDoctor().getAddress().getAddressLine1()
					+ "," + medicinePrescriptionVO.getDoctor().getAddress().getArea();
			String doctorAddress2 = medicinePrescriptionVO.getDoctor().getAddress().getCity() + "-"
					+ medicinePrescriptionVO.getDoctor().getAddress().getZipCode();
			String patientName = "Name: " + medicinePrescriptionVO.getPatient().getFirstName() + " "
					+ medicinePrescriptionVO.getPatient().getLastName();
			String patientDob = "DOB: "
					+ new SimpleDateFormat("dd-MM-yyyy").format(medicinePrescriptionVO.getPatient().getDateOfBirth());
			String patientAge = "Age: " + medicinePrescriptionVO.getPatient().getAge();
			List<PrescribedMedicine> prescribedMedicine = medicinePrescriptionVO.getMedicines();

			List<Paragraph> headerData = new ArrayList<Paragraph>();
			headerData.add(createParagraph(doctorName, 15, 0, 0.0f));
			headerData.add(createParagraph(doctormobile, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress1, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress2, 8, 0, 0.0f));
			headerData.add(createParagraph(patientName, 9, 0, 30.0f));
			headerData.add(createParagraph(patientDob, 8, 0, 0.0f));
			headerData.add(createParagraph(patientAge, 8, 0, 0.0f));

			List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
			columnTitle.add(createColumnTitle("Name", 10, 4.0f));
			columnTitle.add(createColumnTitle("Frequency", 10, 4.0f));
			columnTitle.add(createColumnTitle("Instruction", 10, 4.0f));
			columnTitle.add(createColumnTitle("For Days", 10, 4.0f));

			List<Paragraph> columnvalue = new ArrayList<Paragraph>();
			for (PrescribedMedicine pmTemp : prescribedMedicine)
			{
				columnvalue.add(createParagraph(pmTemp.getDrug().getName() + "" + pmTemp.getDrug().getStrength(), 9, 0,
						0.0f));
				columnvalue.add(createParagraph(pmTemp.getFrequency(), 9, 0, 0.0f));
				columnvalue.add(createParagraph(pmTemp.getDrug().getName() + "" + pmTemp.getDrug().getStrength(), 9, 0,
						0.0f));
				columnvalue.add(createParagraph(String.valueOf(pmTemp.getDuration()) + " days", 9, 0, 0.0f));
			}

			PdfPTable pdfPTable = createTable(4, null, columnTitle, columnvalue, null);
			List<PdfPTable> bodyData = new ArrayList<PdfPTable>();
			bodyData.add(pdfPTable);
			prepareDocument(document, headerData, bodyData);

			file.close();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		return fileToStore.getAbsolutePath();
	}

	public String getDiagnosisPrescriptionPdf(DiagnosisPrescriptionVO diagnosisPrescriptionVO, File directory)
	{
		File fileToStore = null;
		try
		{
			fileToStore = prepareFile(directory);
			OutputStream file = new FileOutputStream(fileToStore);
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			String doctorName = "Dr. " + diagnosisPrescriptionVO.getDoctor().getFirstName() + " "
					+ diagnosisPrescriptionVO.getDoctor().getLastName();
			String doctormobile = "Mobile " + diagnosisPrescriptionVO.getDoctor().getMobile();
			String doctorAddress1 = "Address: " + diagnosisPrescriptionVO.getDoctor().getAddress().getAddressLine1()
					+ "," + diagnosisPrescriptionVO.getDoctor().getAddress().getArea();
			String doctorAddress2 = diagnosisPrescriptionVO.getDoctor().getAddress().getCity() + "-"
					+ diagnosisPrescriptionVO.getDoctor().getAddress().getZipCode();
			String patientName = "Name: " + diagnosisPrescriptionVO.getPatient().getFirstName() + " "
					+ diagnosisPrescriptionVO.getPatient().getLastName();
			String patientDob = "DOB: "
					+ new SimpleDateFormat("dd-MM-yyyy").format(diagnosisPrescriptionVO.getPatient().getDateOfBirth());
			String patientAge = "Age: " + diagnosisPrescriptionVO.getPatient().getAge();
			List<PrescribedDiagnosis> prescribedDiagnosis = diagnosisPrescriptionVO.getPrescribedDiagnosis();

			List<Paragraph> headerData = new ArrayList<Paragraph>();
			headerData.add(createParagraph(doctorName, 15, 0, 0.0f));
			headerData.add(createParagraph(doctormobile, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress1, 8, 0, 0.0f));
			headerData.add(createParagraph(doctorAddress2, 8, 0, 0.0f));
			headerData.add(createParagraph(patientName, 9, 0, 30.0f));
			headerData.add(createParagraph(patientDob, 8, 0, 0.0f));
			headerData.add(createParagraph(patientAge, 8, 0, 0.0f));

			List<PdfPCell> columnTitle = new ArrayList<PdfPCell>();
			columnTitle.add(createColumnTitle("Name", 10, 4.0f));
			columnTitle.add(createColumnTitle("Notes", 10, 4.0f));

			List<Paragraph> columnvalue = new ArrayList<Paragraph>();
			for (PrescribedDiagnosis pmTemp : prescribedDiagnosis)
			{
				columnvalue.add(createParagraph(pmTemp.getDiagnosis().getName(), 9, 0, 0.0f));
				columnvalue.add(createParagraph(pmTemp.getNotes(), 9, 0, 0.0f));
			}
			PdfPTable pdfPTable = createTable(2, null, columnTitle, columnvalue, null);
			List<PdfPTable> bodyData = new ArrayList<PdfPTable>();
			bodyData.add(pdfPTable);
			prepareDocument(document, headerData, bodyData);

			file.close();
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return fileToStore.getAbsolutePath();
	}

	private static File prepareFile(File directory)
	{
		if (!directory.isDirectory())
		{
			directory.mkdirs();
		}
		return new File(directory + File.separator + RandomStringUtils.randomAlphanumeric(15) + ".pdf");
	}

	private static Paragraph createParagraph(String value, int fontSize, int leftSpace, float beforeSpace)
	{
		Paragraph paragraph = new Paragraph(value, new Font(Font.FontFamily.HELVETICA, fontSize));
		paragraph.setIndentationLeft(leftSpace);
		paragraph.setSpacingBefore(beforeSpace);
		return paragraph;
	}

	private static PdfPCell createColumnTitle(String columnName, int fontSize, float padding)
	{
		PdfPCell column = new PdfPCell(new Paragraph(columnName, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
		column.setHorizontalAlignment(Element.ALIGN_CENTER);
		column.setVerticalAlignment(Element.ALIGN_MIDDLE);
		column.setPadding(padding);
		return column;
	}

	private static PdfPTable createTable(int noOfColumn, PdfPCell tableTitle, List<PdfPCell> columnTitle,
			List<Paragraph> columnValue, PdfPCell notesContents)
	{
		PdfPTable table = new PdfPTable(noOfColumn);
		table.setWidthPercentage(100.0f);
		table.setSpacingBefore(30.0f);
		if (tableTitle != null)
			table.addCell(tableTitle);

		if (columnTitle != null)
			for (PdfPCell columnName : columnTitle)
			{
				table.addCell(columnName);
			}
		if (columnValue != null)
			for (Paragraph columnval : columnValue)
			{
				table.addCell(columnval);
			}
		if (notesContents != null)
			table.addCell(notesContents);
		return table;
	}

	private static PdfPCell prepareTableHeader(String headerValue, int colSpan)
	{
		PdfPCell header = new PdfPCell(new Paragraph(headerValue, new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
		header.setColspan(colSpan);
		header.setHorizontalAlignment(Element.ALIGN_LEFT);
		header.setPadding(3.0f);
		header.setBorder(0);
		return header;
	}

	private static void prepareDocument(Document document, List<Paragraph> headerData, List<PdfPTable> bodyData)
			throws Exception
	{
		document.open();
		for (Paragraph header : headerData)
		{
			document.add(header);
		}
		if (!bodyData.isEmpty())
		{
			for (PdfPTable body : bodyData)
			{
				document.add(body);
			}
		}
		document.close();
	}

	private static PdfPCell prepareDocumentNotes(String contents) throws Exception
	{
		PdfPCell content = new PdfPCell(new Paragraph(contents, new Font(Font.FontFamily.HELVETICA, 8)));
		content.setBorder(0);
		return content;
	}*/

}
