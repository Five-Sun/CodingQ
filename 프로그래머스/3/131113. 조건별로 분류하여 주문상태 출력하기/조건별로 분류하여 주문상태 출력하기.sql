SELECT
    ORDER_ID,
    PRODUCT_ID,
    DATE_FORMAT(OUT_DATE, '%Y-%m-%d') AS OUT_DATE, -- COALESCE 제거
    CASE
        WHEN OUT_DATE <= '2022-05-01' THEN '출고완료' -- 부등호 수정
        WHEN OUT_DATE > '2022-05-01' THEN '출고대기'  -- 부등호 수정
        ELSE '출고미정'
    END AS '출고여부'
FROM FOOD_ORDER
ORDER BY ORDER_ID ASC;
