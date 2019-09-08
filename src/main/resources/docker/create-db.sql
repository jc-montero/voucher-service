CREATE TABLE discount
(
    id integer NOT NULL,
    code character varying(25) NOT NULL,
    description character varying(255),
    type character varying(10) NOT NULL,
    value integer NOT NULL,
    CONSTRAINT discount_pkey PRIMARY KEY (id)
);

CREATE TABLE voucher
(
    id integer NOT NULL,
    code character varying(25) NOT NULL,
    discount integer NOT NULL,
    active boolean NOT NULL,
    CONSTRAINT voucher_pkey PRIMARY KEY (id),
    CONSTRAINT fk_discount FOREIGN KEY (discount)
        REFERENCES discount (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO public.discount(id, code, description, type, value)
	VALUES (1, 'GRATIS', 'El descuento es igual al total', 'PORCENTAJE', 100);
INSERT INTO public.discount(id, code, description, type, value)
	VALUES (2, 'FIJO-5', 'Descuenta 5€ al total', 'FIJO', 5);
INSERT INTO public.discount(id, code, description, type, value)
	VALUES (3, 'FIJO-10', 'Descuenta 10€ al total', 'FIJO', 10);
INSERT INTO public.discount(id, code, description, type, value)
	VALUES (4, 'PORCENTAJE-5', 'Descuenta un 5% al total', 'PORCENTAJE', 5);
INSERT INTO public.discount(id, code, description, type, value)
	VALUES (5, 'PORCENTAJE-10', 'Descuenta un 10% al total', 'PORCENTAJE', 10);
	
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (1, 'CUPON-A', 1, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (2, 'CUPON-B', 1, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (3, 'CUPON-C', 2, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (4, 'CUPON-D', 3, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (5, 'CUPON-E', 4, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (6, 'CUPON-F', 5, true);
INSERT INTO public.voucher(id, code, discount, active)
	VALUES (7, 'CUPON-G', 1, false);