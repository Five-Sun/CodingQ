-- 코드를 입력하세요
SELECT
    A.CART_ID
FROM
    (
        SELECT
            CART_ID
        FROM
            CART_PRODUCTS
        WHERE
            NAME = 'Milk'
    ) A
JOIN
    (
        SELECT
            CART_ID
        FROM
            CART_PRODUCTS
        WHERE
            NAME = 'Yogurt'
    ) B
ON A.CART_ID = B.CART_ID
GROUP BY CART_ID
ORDER BY CART_ID