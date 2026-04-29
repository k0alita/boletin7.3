package Pruebas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
    // 1. Solo números
    Pattern p1 = Pattern.compile("^\\d+$");
    // 2. Solo letras minúsculas
    Pattern p2 = Pattern.compile("^[a-z]+$");
    // 3. Solo letras mayúsculas
    Pattern p3 = Pattern.compile("^[A-Z]+$");
    // 4. Alfanumérico (letras y números)
    Pattern p4 = Pattern.compile("^[a-zA-Z0-9]+$");
    // 5. Palabra que empieza con mayúscula
    Pattern p5 = Pattern.compile("^[A-Z][a-z]*$");
    // 6. Código binario (solo 0 y 1)
    Pattern p6 = Pattern.compile("^[01]+$");
    // 7. Contiene al menos un espacio
    Pattern p7 = Pattern.compile(".*\\s.*");
    // 8. Número entero positivo o negativo
    Pattern p8 = Pattern.compile("^-?\\d+$");
    // 9. Hexadecimal simple
    Pattern p9 = Pattern.compile("^[0-9A-Fa-f]+$");
    // 10. Cadena vacía o en blanco
    Pattern p10 = Pattern.compile("^\\s*$");

    // 11. DNI o NIE español (formato básico)
    Pattern p11 = Pattern.compile("^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$");
    // 12. Nombre completo (nombres con espacios y tildes)
    Pattern p12 = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    // 13. Nombre de usuario (Instagram/TikTok style, 3-15 chars)
    Pattern p13 = Pattern.compile("^[a-zA-Z0-9_.-]{3,15}$");
    // 14. Código postal de España (5 dígitos válidos)
    Pattern p14 = Pattern.compile("^(?:0[1-9]|[1-4]\\d|5[0-2])\\d{3}$");
    // 15. Teléfono móvil español (9 dígitos empezando por 6 o 7)
    Pattern p15 = Pattern.compile("^[67]\\d{8}$");
    // 16. Teléfono internacional (prefijo +XX opcional)
    Pattern p16 = Pattern.compile("^(\\+\\d{1,3})?\\s?\\d{9,15}$");
    // 17. Pasaporte estándar (6-9 caracteres)
    Pattern p17 = Pattern.compile("^[A-Z0-9]{6,9}$");
    // 18. Edad entre 18 y 99
    Pattern p18 = Pattern.compile("^(1[89]|[2-9]\\d)$");
    // 19. Género (M, F, O)
    Pattern p19 = Pattern.compile("^[MFO]$");
    // 20. Nick de juego (letra seguida de letras/números)
    Pattern p20 = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]{2,15}$");

    // 21. Dirección IPv4
    Pattern p21 = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    // 22. Dirección MAC
    Pattern p22 = Pattern.compile("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
    // 23. Puerto de red (0 a 65535)
    Pattern p23 = Pattern.compile("^(6553[0-5]|655[0-2]\\d|65[0-4]\\d{2}|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3}|0)$");
    // 24. Color Hexadecimal (#RGB o #RRGGBB)
    Pattern p24 = Pattern.compile("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");
    // 25. Archivo Java (.java)
    Pattern p25 = Pattern.compile("^[\\w-]+\\.java$");
    // 26. Ruta absoluta de Linux
    Pattern p26 = Pattern.compile("^(/[a-zA-Z0-9_-]+)+/?$");
    // 27. Etiqueta HTML simple
    Pattern p27 = Pattern.compile("^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$");
    // 28. UUID / GUID v4
    Pattern p28 = Pattern.compile("^[0-9a-f]{8}-[0-9a-f]{4}-4[0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$");
    // 29. Dominio web básico
    Pattern p29 = Pattern.compile("^[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$");
    // 30. URL segura (HTTPS)
    Pattern p30 = Pattern.compile("^https:\\/\\/[\\w\\.-]+\\.[a-z]{2,}(\\/.*)?$");

    // 31. Precio (hasta dos decimales)
    Pattern p31 = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    // 32. Precio en Euros
    Pattern p32 = Pattern.compile("^\\d+(\\.\\d{1,2})?€$");
    // 33. Tarjeta de crédito (16 dígitos, guiones opcionales)
    Pattern p33 = Pattern.compile("^\\d{4}-?\\d{4}-?\\d{4}-?\\d{4}$");
    // 34. Código CVV (3 dígitos)
    Pattern p34 = Pattern.compile("^\\d{3}$");
    // 35. Expiración tarjeta (MM/YY)
    Pattern p35 = Pattern.compile("^(0[1-9]|1[0-2])\\/\\d{2}$");
    // 36. IBAN Español (ES + 22 dígitos)
    Pattern p36 = Pattern.compile("^ES\\d{22}$");
    // 37. Referencia producto (PRD-1234)
    Pattern p37 = Pattern.compile("^PRD-\\d{4}$");
    // 38. Porcentaje (0 al 100)
    Pattern p38 = Pattern.compile("^(100|[1-9]?[0-9])$");
    // 39. Código de descuento (5-10 chars mayúsculas/números)
    Pattern p39 = Pattern.compile("^[A-Z0-9]{5,10}$");
    // 40. Hora formato 24h (HH:MM)
    Pattern p40 = Pattern.compile("^([01]\\d|2[0-3]):([0-5]\\d)$");

    // 41. Matrícula actual en España (4 números, 3 letras válidas)
    Pattern p41 = Pattern.compile("^[0-9]{4}[BCDFGHJKLMNPRSTVWXYZ]{3}$");
    // 42. Matrícula antigua España (Letras provinciales + 4 números + 1/2 letras)
    Pattern p42 = Pattern.compile("^[A-Z]{1,2}\\d{4}[A-Z]{1,2}$");
    // 43. Bastidor de vehículo (17 caracteres sin I, O, Q)
    Pattern p43 = Pattern.compile("^[A-HJ-NPR-Z0-9]{17}$");
    // 44. Email estándar
    Pattern p44 = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    // 45. Contraseña fuerte (Min 8, 1 Mayus, 1 Minus, 1 Num, 1 Especial)
    Pattern p45 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    // 46. Fecha estricta (DD/MM/YYYY)
    Pattern p46 = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[\\/\\-](0[1-9]|1[012])[\\/\\-]\\d{4}$");
    // 47. Semantic Versioning (ej: 1.0.4-beta)
    Pattern p47 = Pattern.compile("^\\d+\\.\\d+\\.\\d+(-[a-zA-Z0-9\\.-]+)?$");
    // 48. Base64
    Pattern p48 = Pattern.compile("^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$");
    // 49. Coordenadas GPS (Lat, Long)
    Pattern p49 = Pattern.compile("^[-+]?([1-8]?\\d(\\.\\d+)?|90(\\.0+)?),\\s*[-+]?(180(\\.0+)?|((1[0-7]\\d)|([1-9]?\\d))(\\.\\d+)?)$");
    // 50. Extraer Hashtags (usa p50.matcher(texto).find() para buscar)
    Pattern p50 = Pattern.compile("(?<=#)\\w+");

    // 1) Alejandro Hernandez (sin tildes se quita las letras con tilde)
    Pattern pattern1 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");

    // 2) Alejandro Hernandez Santos
    Pattern pattern2 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");

    // 3) Alejandro Hernandez 674673467
    Pattern pattern3 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s\\d{9}$");

    // 4) Alejandro Hernandez Santos 674673467
    Pattern pattern4 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s\\d{9}$");

    // 5) Alejandro Hernandez Dos Santos
    Pattern pattern5 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\sDos\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");

    // 6) Alejandro Hernandez Dos Santos 674673467
    Pattern pattern6 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\sDos\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s\\d{9}$");

    // 7) Alejandro Manuel Hernandez Santos
    Pattern pattern7 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");

    // 8) Alejandro Manuel Hernandez Dos Santos
    Pattern pattern8 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\sDos\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+$");

    // 9) Alejandro Manuel Hernandez Santos 674673467
    Pattern pattern9 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s\\d{9}$");

    // 10) Alejandro Manuel Hernandez Dos Santos 674673467
    Pattern pattern10 = Pattern.compile("^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\sDos\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s\\d{9}$");

    // 1) Alejandro Hernandez
    Pattern patternG1 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)$"
    );

    // 2) Alejandro Hernandez Santos
    Pattern patternG2 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)$"
    );

    // 3) Alejandro Hernandez 674673467
    Pattern patternG3 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<telefono>\\d{9})$"
    );

    // 4) Alejandro Hernandez Santos 674673467
    Pattern patternG4 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<telefono>\\d{9})$"
    );

    // 5) Alejandro Hernandez Dos Santos
    Pattern patternG5 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<particula>Dos)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)$"
    );

    // 6) Alejandro Hernandez Dos Santos 674673467
    Pattern patternG6 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<particula>Dos)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<telefono>\\d{9})$"
    );

    // 7) Alejandro Manuel Hernandez Santos
    Pattern patternG7 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<nombre2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)$"
    );

    // 8) Alejandro Manuel Hernandez Dos Santos
    Pattern patternG8 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<nombre2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<particula>Dos)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)$"
    );

    // 9) Alejandro Manuel Hernandez Santos 674673467
    Pattern patternG9 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<nombre2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<telefono>\\d{9})$"
    );

    // 10) Alejandro Manuel Hernandez Dos Santos 674673467
    Pattern patternG10 = Pattern.compile(
            "^(?<nombre1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<nombre2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<apellido1>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<particula>Dos)\\s(?<apellido2>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)\\s(?<telefono>\\d{9})$"
    );

    Pattern patternFlexible = Pattern.compile(
            "^(?<nombreCompleto>[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(?:\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)*(?:\\sDos\\s[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+)?)(?:\\s(?<telefono>\\d{9}))?$"
    );

    public static void main(String[] args) {
        String texto = "Alejandro Hernandez Dos Santos 674673467";

        Pattern pattern = Pattern.compile(
                "^(?<nombre1>[A-Z][a-z]+)\\s" +
                        "(?<apellido1>[A-Z][a-z]+)\\s" +
                        "(?<particula>Dos)\\s" +
                        "(?<apellido2>[A-Z][a-z]+)\\s" +
                        "(?<telefono>\\d{9})$"
        );

        Matcher matcher = pattern.matcher(texto);

        if (matcher.matches()) {
            System.out.println("Nombre: " + matcher.group("nombre1"));
            System.out.println("Apellido 1: " + matcher.group("apellido1"));
            System.out.println("Partícula: " + matcher.group("particula"));
            System.out.println("Apellido 2: " + matcher.group("apellido2"));
            System.out.println("Teléfono: " + matcher.group("telefono"));
        }
    }


}
