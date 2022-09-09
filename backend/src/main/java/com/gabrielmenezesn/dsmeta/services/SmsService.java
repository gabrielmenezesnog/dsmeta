package com.gabrielmenezesn.dsmeta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gabrielmenezesn.dsmeta.entities.Sale;
import com.gabrielmenezesn.dsmeta.repositories.SaleRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class SmsService {

  // @Value buscará em aplication.properties
  // a variável de ambiente correspondente
  // ao seu parâmetro e a guardará em sua
  // variável abaixo
  @Value("${twilio.sid}")
  private String twilioSid;

  @Value("${twilio.key}")
  private String twilioKey;

  @Value("${twilio.phone.from}")
  private String twilioPhoneFrom;

  @Value("${twilio.phone.to}")
  private String twilioPhoneTo;

  // Método para enviar SMS
  // reproduzido com o auxílio da documentação Twilio
  // https://www.twilio.com/docs/sms/quickstart/java
  // saleId é o parâmetro para o ID
  // da venda
  @Autowired
  private SaleRepository saleRepository;

  public void sendSms(Long saleId) {

    // procura em SaleRepository o id passado em sendSms
    Sale sale = saleRepository.findById(saleId).get();

    // pegando mês e ano MM/YYYY
    String date = sale.getDate().getMonthValue() + "/" + sale.getDate().getYear();

    String msg = "O vendedor " + sale.getSellerName() + " foi destaque em " + date
        + " com um total de R$ " + String.format("%.2f", sale.getAmount());

    Twilio.init(twilioSid, twilioKey);

    PhoneNumber to = new PhoneNumber(twilioPhoneTo);
    PhoneNumber from = new PhoneNumber(twilioPhoneFrom);

    Message message = Message.creator(to, from, msg).create();

    System.out.println(message.getSid());
  }
}
