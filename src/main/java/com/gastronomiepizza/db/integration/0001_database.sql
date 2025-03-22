CREATE DATABASE grastropizza;

do
$$
begin
    if not exists (select from pg_type where typename = unit)then
        CREATE TYPE unit AS ENUM ('G', 'L', 'U');
    end if;
end
$$