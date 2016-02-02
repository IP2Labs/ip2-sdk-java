/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ug.co.intelworld.constants;

/**
 *
 * @author herbert
 */
public class IP2Codes {
  
    
    public static enum Environment{
        SANDBOX,PRODUCTION
    }    
    public static enum CountryCodes{
        UGANDA,KENYA,RWANDA,TANZANIA,BURUNDI;
        
        public static String getCountryCode(CountryCodes code){
            switch(code){
                case UGANDA:
                    return "256";
                case KENYA:
                    return "254";
                case RWANDA:
                    return "250";
                case TANZANIA:
                    return "255";
                case BURUNDI:
                    return "257";
                default:
                    return "256";
            }
        }
        
    }
    
    public static enum ServiceID{
        //Buy airtime - transferto, wallet topup - wallettopup, sell airtime - airtimesend, send money - moneysend, tiketi
        BUY_AIRTIME,WALLET_TOPUP,SELL_AIRTIME,SEND_MONEY,ALTXSECURITIESTRADING,TIKETI
    }
    
    public static enum CurrencyCodes{
        UGX,KES,RWF,TZS,BIF
    
    }
    
    public static enum Providers{
        MTNMOBILEMONEY,AIRTELMONEY,MICROPAY,MTNAIRTIME
    }
    
    public static enum Channels{
        ANDROIDAPP,SMS,WEBAPP,WEBAPI,USSD
    }
    
    public static enum TransactionType{
        DEBIT,CREDIT
    }
    
}
