# bf-api-paymentOptions-code-analyzer
Analiza la estructura atributos de una clase groovy y genera una clase Java con la misma estructura
El archivo raaiz desde donde inciar el analisis de los codigo fuente groovy esta en la clase FileProcessor.java, en el metodo initialize()

public void initialize(){
toParseList.add(new ClassToParse("PaymentOptionsResponse.groovy",
"/Users/gwolfmann/Downloads/buyingflow-api/target/work/plugins/buyingflow-commons-1.317.0/src/groovy/buyingflow/dto/response/",
Boolean.FALSE));
}

por lo que hay que cambiar el path para que lea el archivo deseado

Los archivos java los crea en una subdirectorio /src/generated
Objetivo: realizar un diff del archivo generado con el archivo final en bf-payments

@Todo para automatizar el proceso
1) A partir del archivo groovy raiz (  PaymentOptionsResponse.groovy) leer los campos que lo compone, y aquellos que no sean primitivos, scanearlos para realizar el mismo proceso
2) Con los archivos Java generados, comparar contra los archivos existentes en bf-payments para detectar, falta de archivos o falta o modificacion en las estructuras de clases
