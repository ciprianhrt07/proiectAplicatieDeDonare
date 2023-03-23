# proiectAplicatieDeDonare


#Pentru rularea aplicatiei:
  -> se descarca arhiva cu proiectul
  -> se creeaza o baza de data cu numele center in MySqlWorkbench
  -> se creeaza un admin folosind sql:
      insert into application_users(user_type_id , active ,   password , role , username)
values(1 ,  true , "admin", "ROLE_ADMIN" , "admin" ); 
  -> restul userilor se pot crea sterge sau adauga ulterior, doar adminul trebuie inserat manual.
  GL , HF.
