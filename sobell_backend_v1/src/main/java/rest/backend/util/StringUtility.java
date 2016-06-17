package rest.backend.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class StringUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtility.class);
    public static String LINE_SEP = System.getProperty("line.separator");
    public static final String[] SPLIT_DELIMS = {",", " ", "\n"};

    public static boolean isEmail(String email) throws Exception {
        boolean isEmail = false;

        Pattern pattern = null;
        Matcher matcher = null;

        pattern = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        matcher = pattern.matcher(email);

        if (matcher.find()) {
            isEmail = true;
        }
        return isEmail;
    }

    public static boolean isValidDNI(String dni) {
        return StringUtils.hasText(dni) && dni.length() == 8;
    }

    public static boolean isValidRUC(String ruc) {

        if (ruc == null || ruc.equals("")) return true; // nothing to validate
        ruc = ruc.trim();
        // longitud
        if (ruc.length() != 11) {
//            logger.info("RUC[" + ruc + "] no tiene 11 digitos");
            return false;
        }

        // digitos
        Character noDigito = findNonDigit(ruc);
        if (noDigito != null) {
//            logger.info("RUC[" + ruc + "] tiene caracter no valido:'" + noDigito + "'");
            return false;
        }

        String rucPrefix = ruc.substring(0, 2);
        List rucSerieList = Arrays.asList(new String[]{"10", "15", "20", "17"});
        if (rucSerieList.indexOf(rucPrefix) < 0) {
            // ruc NO pertenece a la serie
            return false;
        }
        int suma = 0, x = 6;
        for (int i = 0; i < 10; i++) {
            if (i == 4) x = 8;
            int digito = ruc.charAt(i) - '0';
            x--;
            //Nota esto hace lo mismo, pero esta asi en el SP, confirmar si hay error
            if (i == 0) suma += digito * x;
            else suma += digito * x;
        }
        int resto = suma % 11;
        resto = 11 - resto;
        if (resto >= 10) resto -= 10;

        int ultDigito = ruc.charAt(10) - '0';
        boolean rucOk = resto == ultDigito;
        if (!rucOk) {
//            logger.info("RUC[" + ruc + "] digito de verificacion incorrecto calculado:'" + resto + "'");
            return false;
        }
        return true;
    }

    private static Character findNonDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)))
                return new Character(str.charAt(i));
        }
        return null;
    }


    public static String concatSeals(String... str) {
        String buf = "";
        List<String> invalidSeals = Arrays.asList("-", "SP", "SINPRESINTO", "SINPRESINTOS",
                "SINPRECINTO", "SINPRECINTOS", "SPFLAT", "S8P", "S&P");
        for (String seal : str) {
            seal = sealFixContent(seal); //eliminar caracteres invalidos
            boolean dataValid = StringUtils.hasText(seal) && !invalidSeals.contains(seal);
            if (buf.length() > 0 && dataValid) buf += "/";
            if (dataValid) buf += seal;
        }
        return buf;
    }

    public static BigDecimal toBigDecimal(String str) {
        if (str == null) return null;
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException ignored) {
            return null;
        }
    }

    private static String sealFixContent(String sealO) {
        String seal = sealO.replace("/", "");
        seal = seal.replace('.', ' ');
        seal = seal.replace(" ", "");
        seal = seal.toUpperCase();
        return seal;
    }

    public static boolean hasText(String str) {
        return StringUtils.hasText(str);
    }

    /**
     * @param manifestFull manifext 2010-20001
     * @return 10
     */
    public static String manifestYear2(String manifestFull) {
        return manifestFull.substring(2, 4);
    }

    /**
     * @param manifestFull manifext 2010-20001
     * @return 2010
     */
    public static String manifestYear4(String manifestFull) {
        return manifestFull.substring(0, 4);
    }

    /**
     * @param manifestFull manifext 2010-20001
     * @return 20001
     */
    public static String manifestOnly(String manifestFull) {
        return manifestFull.substring(5);
    }

    public static String rpad(String str, int size) {
        return padIntern(str, size, ' ', true);
    }

    public static String rpad(String str, int size, char filler) {
        return padIntern(str, size, filler, true);
    }

    public static String lpad(Object str, int size, char filler) {
        return padIntern(str == null ? "" : String.valueOf(str), size, filler, false);
    }

    private static String padIntern(String str, int size, char filler, boolean right) {
        if (str == null) str = "";
        if (str.length() > size) str = str.substring(0, size - 1);
        if (str.length() < size) {
            StringBuffer strBuf = new StringBuffer(size);
            strBuf.append(str);
            while (strBuf.length() < size) {
                if (right) strBuf.insert(0, filler);
                else strBuf.append(filler);
            }
            str = strBuf.toString();
        }
        return str;
    }

    public static void mainX(String[] args) {
        System.out.println(new DecimalFormat("#,##0.00").format(4546.56));
        System.out.println(manifestYear2("2010-20001"));
        System.out.println(manifestYear4("2010-20001"));
        System.out.println(manifestOnly("2010-20001"));
        System.out.println("0,1,,3".split(",").length);
    }

    public static String concat(Collection list, String sep) {
        return concat(list, sep, null);
    }

    public static String concat(Collection list, String sep, String enclosing) {
        StringBuffer buf = new StringBuffer();
        for (Object str : list) {
            if (buf.length() > 0) buf.append(sep);
            if (enclosing != null) buf.append(enclosing);
            buf.append(str);
            if (enclosing != null) buf.append(enclosing);
        }
        return buf.toString();
    }


    public static String nvl(String s1, String s2) {
        return hasText(s1) ? s1 : s2;
    }

    public static String regionExtract(StringBuffer buf, String startRegion, String endRegion, String replacement) {
        int idxStart = startRegion == null ? 0 : buf.indexOf(startRegion);
        if (idxStart == -1)
            throw new IllegalArgumentException("Region start " + startRegion + " no encontrado en " + buf);
        int idxEnd = buf.indexOf(endRegion);
        if (idxEnd == -1) throw new IllegalArgumentException("Region start " + endRegion + " no encontrado en " + buf);
        String regionExtract = buf.substring(idxStart + (startRegion == null ? 0 : startRegion.length()), idxEnd);
        buf.replace(idxStart, idxEnd + endRegion.length(), replacement);
        return regionExtract;
    }

    public static String trunc(String str, int maxLenght) {
        return str == null || str.length() < maxLenght ? str : str.substring(0, maxLenght);
    }

    public static boolean isEmpty(String str) {
        return !hasText(str);
    }

    public CharSequence removeStartEndLineFeed(CharSequence str) {
        StringBuffer buf = str instanceof StringBuffer ? (StringBuffer) str : new StringBuffer(str);
        if (buf.charAt(0) == 13 || buf.charAt(0) == 10) buf.deleteCharAt(0);
        if (buf.charAt(buf.length() - 1) == 13 || buf.charAt(buf.length() - 1) == 10)
            buf.deleteCharAt(buf.length() - 1);
        return buf;
    }

    public static String replaceFull(String str, Map<String, String> params) {
        StringBuffer buf = new StringBuffer(str);
        for (String valueOld : params.keySet()) {
        	LOGGER.debug("Aplicando parametro " + valueOld + " " + params.get(valueOld));
            String valueNew = params.get(valueOld);
            replace(buf, valueOld, valueNew);
        }
        return buf.toString();
    }

    public static StringBuffer replace(StringBuffer buf, String valueOld, String valueNew) {
        while (true) {
            int idx = buf.indexOf(valueOld);
            if (idx == -1) {
                break;
            }
            buf.replace(idx, idx + valueOld.length(), valueNew);
        }
        return buf;

    }

    public static String replace(String buf, String valueOld, String valueNew) {
        return replace(new StringBuffer(buf), valueOld, valueNew).toString();
    }

    public static int lineCount(String str) {
        int idx = 0;
        int count = 0;
        while (true) {
            idx = str.indexOf(LINE_SEP, idx + 1);
            if (idx < 0) break;
            count++;
        }
        return count;
    }

    public static Integer parseIntNull(String str) {
        try {
            return new Integer(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long parseLongNull(String val) {
        if (val == null) return null;
        try {
            return new Long(val);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static BigDecimal parseBigDecimalNull(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public static boolean validateTextWithPattern(String texto, String patternStr) {
        boolean isCorrect = false;
        Pattern pattern = null;
        Matcher matcher = null;
        try {
            if (texto != null && patternStr != null) {
                pattern = Pattern.compile(patternStr);
                matcher = pattern.matcher(texto);

                if (matcher.find()) {
                    isCorrect = true;
                }
            }
            return isCorrect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isCorrect;
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * @param str        por ejemplo  Param1 ( param2 , param3 , param4 )
     * @param leftDelim  (
     * @param rigthDelim )
     * @param innerDelim , (optional)
     * @return
     */
    public static List<String> parseFormattedInfo(String str, String leftDelim, String rigthDelim, String innerDelim) {
        List<String> result = new ArrayList<String>();
        StringBuffer buf = new StringBuffer(str.lastIndexOf(rigthDelim) >= 0 ? str.substring(0, str.lastIndexOf(rigthDelim)) : str);
        String mainPart = regionExtract(buf, null, leftDelim, "");
        if (mainPart != null) result.add(mainPart);
        while (buf.length() > 0 && innerDelim != null) {
            String innerPart = regionExtract(buf, null, innerDelim, "");
            if (innerPart != null) result.add(innerPart);
        }
        if (buf.length() > 0) result.add(buf.toString());
        return result;
    }

    public static BigDecimal toNonZeroBigDecimal(String str) {
        BigDecimal number = toBigDecimal(str);
        if (number != null && number.doubleValue() == 0) number = null;
        return number;
    }

    public static List<String> split(String str, String... delims) {
        StringBuffer buf = new StringBuffer(str == null ? "" : str.trim());
        List<String> result = new ArrayList<String>();
        while (buf.length() > 0) {
            String valToAdd = null;
            int idx = firstIdx(buf.toString(), null, delims);
            if (idx < 0) idx = buf.length();
            valToAdd = buf.substring(0, idx);
            buf.replace(0, idx + 1, ""); // remover
            valToAdd = StringUtility.trim(valToAdd);
            if (StringUtility.hasText(valToAdd)) result.add(valToAdd);
        }
        return result;
    }


    public static int firstIdx(String str, Integer fromIdx, String... delims) {
        if (fromIdx == null) fromIdx = 0;
        int bestIdx = -1;
        for (String delim : delims) {
            int idx = str.indexOf(delim, fromIdx);
            if (idx != -1 && (bestIdx == -1 || idx < bestIdx)) bestIdx = idx;
        }
        return bestIdx;
    }

    public static List<String> upperCase(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).toUpperCase());
        }
        return list;
    }

    public static String toJavaName(String dbColName) {
        String[] parts = dbColName.toLowerCase().trim().split("_");
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            if (part.length() > 0)
                parts[i] = part.substring(0, 1).toUpperCase() + (part.length() > 1 ? part.substring(1) : "");
        }
        return concat(Arrays.asList(parts), "");
    }

    public static String last(String str, int size) {
        int length = str.length();
        if (length <= size) return str;
        else return str.substring(length - size);
    }
    
    public static boolean isNotNull(String txt){
    	return txt != null && txt.trim().length() >= 0 ? true : false;
    }
    
    public static String constructJSON(String tag, boolean status) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
		} catch (JSONException e) {
		}
		return obj.toString();
	}
    
    public static String constructJSON(String tag, boolean status,String err_msg) {
		JSONObject obj = new JSONObject();
		try {
			obj.put("tag", tag);
			obj.put("status", new Boolean(status));
			obj.put("error_msg", err_msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		return obj.toString(); 
	}

}
