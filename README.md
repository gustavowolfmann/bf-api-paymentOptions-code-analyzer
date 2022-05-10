# bf-api-paymentOptions-code-analyzer
Analiza la estructura atributos de una clase groovy y genera una clase Java con la misma estructura
El archivo fuente groovy esta hardcoded en el main
        ParserGroovy.parsing("/Users/gwolfmann/Downloads/groovy-sintactic-analizer/src/main/java/Analizer/PaymentOptionsResponse.groovy");
por lo que hay que cambiar el path para que lea el archivo deseado
El archivo java lo crea en una subdirectorio /src/generated
Objetivo: realizar un diff del archivo generado con el archivo final en bf-payments

@Todo para automatizar el proceso
1) A partir del archivo groovy raiz (  PaymentOptionsResponse.groovy) leer los campos que lo compone, y aquellos que no sean primitivos, scanearlos para realizar el mismo proceso
2) Con los archivos Java generados, comparar contra los archivos existentes en bf-payments para detectar, falta de archivos o falta o modificacion en las estructuras de clases
