CREATE TABLE products (
  id NUMBER PRIMARY KEY,
  name VARCHAR2(100) NOT NULL,
  registration_date VARCHAR2(50) NOT NULL
);


CREATE OR REPLACE PROCEDURE insert_product (
  p_id IN products.id%TYPE,
  p_name IN products.name%TYPE,
  p_registration_date IN products.registration_date%TYPE,
  p_product_cursor OUT SYS_REFCURSOR,
  p_return_code OUT NUMBER,
  p_return_message OUT VARCHAR2
)
IS
BEGIN

INSERT INTO products (id, name, registration_date)
VALUES (p_id, p_name, p_registration_date);

OPEN p_product_cursor FOR
SELECT id, name, registration_date
FROM products;

p_return_code := 0;
p_return_message := 'Ejecucion con exito';

EXCEPTION
  WHEN OTHERS THEN
    p_return_code := 1;
p_return_message := 'Error al insertar el producto: ' || SQLERRM;
END;