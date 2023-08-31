package co.phea.api.account.web;

import co.phea.api.accounttype.AccountType;

import java.math.BigDecimal;

public record CreateAccountDto(String actName,

                               String actNo,

                               BigDecimal transferLimit,

                               AccountType accountType
                               ) {
        }
