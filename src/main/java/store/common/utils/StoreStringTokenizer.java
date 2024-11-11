package store.common.utils;


import store.common.exception.StoreException;
import store.product.domain.Product;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StoreStringTokenizer {
    private static final String ORDER_FORM = "\\[(.+)-(\\d+)]";
    private static final String ORDER_FORM_DELIMETER = ",";

    public static List<Map<String,Object>> getListOfPurchaseItemList(String input){
        List<Map<String,Object>> list = new ArrayList<>();
        for(String inputData : splitByDelimeter(input)){
            Map<String, Object> map = tokenizerStringAndInteger(inputData);
            list.add(map);
        }
        return list;
    }
    // .md 파일을 쪼개서 데이터 저장시켜 주는 유틸
    public static List<Product> loadProductsFromFile(String filePath) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int price = Integer.parseInt(values[1]);
                int stock = Integer.parseInt(values[2]);
                String promotionName = values[3].equals("null") ? null : values[3];

                Product product = new Product(name, price, stock, promotionName);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static List<String> splitByDelimeter(String input) {
        return Arrays.stream(input.split(ORDER_FORM_DELIMETER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public static Map<String, Object> tokenizerStringAndInteger(String input){
        Matcher matcher = getMatcher(input);
        Map<String, Object> map = new HashMap<>();
        try{
            if(matcher.matches()){
                map.putAll(Map.of("stringData", matcher.group(1), "intData", matcher.group(2)));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return map;
    }

    public static Matcher getMatcher(String input){
        Pattern pattern = Pattern.compile(ORDER_FORM);
        Matcher matcher = pattern.matcher(input);
        if(!matcher.matches()){
            throw new IllegalArgumentException(StoreException.INVALID_ORDER_FORMAT.getMessage());
        }
        return matcher;
    }

}