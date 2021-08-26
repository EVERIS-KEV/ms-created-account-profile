package com.everis.createdaccountprofile.Constants;

public enum Constants {
    ;
    public static class Messages {
        
        public static final String CLIENT_NOT_REGISTERED = "Cliente no registrado";
        public static final String NEED_CREDIT = "Necesita adquirir un credito en este banco.";

        public static final String YOUR_ACCOUNT_NOFOUND = "Tu número es incorrecto.";

        public static final String INCORRECT_OPERATION = "Operacion incorrecta.";
        public static final String SUCCESS_OPERATION = "Operacion realizada.";
        public static final String REPETED_DATA = "Datos repetidos.";
        public static final String INCORRECT_DATA = "Datos incorrectos.";

        public static final String AMOUNTH_INSUFFICIENT = "Cantidad insuficiente.";
    }

    public static class Path{

        private static final String IPR = "host.docker.internal"; 
        private static final String PORT = "8090";  

        private static final String GATEWAY = IPR.concat(":").concat(PORT);
        private static final String SERVICE_PATH = "/service";
        private static final String HTTP_CONSTANT = "http://";

        public static final String LOGIC_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/logic");
        public static final String CUSTOMERS_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/customers");
        public static final String CREDIT_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/credits");
        
        public static final String CURRENT_ACCOUNT_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/currentAccount");
        public static final String FIXED_ACCOUNT_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/fixedTermAccount");
        public static final String SAVED_ACCOUNT_PATH = HTTP_CONSTANT.concat(GATEWAY).concat(SERVICE_PATH).concat("/savingAccount");
    }

}