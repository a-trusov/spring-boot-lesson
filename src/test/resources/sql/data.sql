insert into company (id, name)
values (1, 'Apple'),
       (2, 'Google');

select setval('company_id_seq', (select max(id) from company));

INSERT INTO public.employee (id, first_name, last_name, birth_day, salary, company_id)
VALUES (1, 'Ivan', 'Ivanov', '2021-03-30', 1000, 1);
INSERT INTO public.employee (id, first_name, last_name, birth_day, salary, company_id)
VALUES (2, 'Petr', 'Petrov', '2021-03-29', 2000, 2);