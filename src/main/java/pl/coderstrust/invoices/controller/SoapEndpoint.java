package pl.coderstrust.invoices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import pl.coderstrust.invoices.database.InvoiceBookException;
import pl.coderstrust.invoices.model.Invoice;
import pl.coderstrust.invoices.model.soap.AddInvoiceRequest;
import pl.coderstrust.invoices.model.soap.AddInvoiceResponse;
import pl.coderstrust.invoices.model.soap.GetInvoiceRequest;
import pl.coderstrust.invoices.model.soap.GetInvoiceResponse;
import pl.coderstrust.invoices.model.soap.InvoiceSoap;
import pl.coderstrust.invoices.model.soap.RemoveInvoiceRequest;
import pl.coderstrust.invoices.model.soap.RemoveInvoiceResponse;
import pl.coderstrust.invoices.model.soap.soapconverter.ConverterSoap;
import pl.coderstrust.invoices.service.InvoiceBook;

@Endpoint
public class SoapEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  private InvoiceBook invoiceBook;
  private ConverterSoap converterSoap;

  @Autowired
  public SoapEndpoint(InvoiceBook invoiceBook, ConverterSoap converterSoap) {
    this.invoiceBook = invoiceBook;
    this.converterSoap = converterSoap;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getInvoiceRequest")
  @ResponsePayload
  public GetInvoiceResponse getInvoice(@RequestPayload GetInvoiceRequest request)
      throws InvoiceBookException {
    GetInvoiceResponse response = new GetInvoiceResponse();
    Invoice invoice = invoiceBook.getInvoice(request.getId());
    response.setInvoice(converterSoap.getInvoiceSoap(invoice));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addInvoiceRequest")
  @ResponsePayload
  public AddInvoiceResponse saveInvoice(@RequestPayload AddInvoiceRequest request)
      throws InvoiceBookException {
    AddInvoiceResponse response = new AddInvoiceResponse();
    InvoiceSoap invoiceSoap = new InvoiceSoap();
    invoiceSoap.setId(request.getId());
    invoiceSoap.setNumber(request.getNumber());
    invoiceSoap.setIssueDate(request.getIssueDate());
    invoiceSoap.setIssuePlace(request.getIssuePlace());
    invoiceSoap.setSellDate(request.getSellDate());
    invoiceSoap.setSeller(request.getSeller());
    invoiceSoap.setBuyer(request.getBuyer());
    for (int i = 0; i < request.getEntries().size(); i++) {
      invoiceSoap.getEntries().add(request.getEntries().get(i));
    }
    Invoice invoice = converterSoap.getInvoice(invoiceSoap);
    invoiceBook.saveInvoice(invoice);
    response.setInvoice(invoiceSoap);
    return response;
  }


  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "removeInvoiceRequest")
  @ResponsePayload
  public RemoveInvoiceResponse removeInvoice(@RequestPayload RemoveInvoiceRequest request)
      throws InvoiceBookException {
    RemoveInvoiceResponse response = new RemoveInvoiceResponse();
    Invoice invoice = invoiceBook.getInvoice(request.getId());
    invoiceBook.removeInvoice(request.getId());
    InvoiceSoap invoiceSoap = converterSoap.getInvoiceSoap(invoice);
    response.setInvoice(invoiceSoap);
    return response;
  }
}