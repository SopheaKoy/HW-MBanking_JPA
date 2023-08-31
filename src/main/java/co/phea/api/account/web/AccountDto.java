package co.phea.api.account.web;

import co.phea.api.accounttype.AccountType;
import java.math.BigDecimal;

public record AccountDto(String uuid,

         String actName,

         String actNo,

         boolean isDeleted,

         BigDecimal transferLimit) {
}
