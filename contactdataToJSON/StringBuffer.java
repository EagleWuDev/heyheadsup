public void readContacts(){

        HashMap<String, String> contacts = new HashMap<>();
        ArrayList <String> info = new ArrayList<>();
        StringBuffer output = new StringBuffer();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if(cur.getCount() > 0){
            while(cur.moveToNext()){
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if(Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
                    output.append(" [\"name\" :" + "\"" + name + "\"");
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);

                    while (pCur.moveToNext()) {
                        String phone = " phoneNumber: " + pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                        int phonetype = pCur.getInt(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        String customLabel = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                        String phoneLabel = " phoneType: " + (String) ContactsContract.CommonDataKinds.Email.getTypeLabel(this.getResources(), phonetype, customLabel);
                        output.append("\"" + phone + "\"");
                        output.append(phoneLabel);



                    }
                    pCur.close();

                    Cursor emailCur = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " =?",
                            new String[]{id}, null);

                    while (emailCur.moveToNext()) {
                        String email = " email: " +emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        String emailType = "EmailType " + emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                        output.append("\"" + email + "\" ]");

                    }
                    emailCur.close();

                    }

            }


        }cur.close();

        contacts.put("contacts", output.toString());

        Gson gson = new Gson();
        String jsonContacts = gson.toJson(contacts);
        String[] TO = {"ewu1@babson.edu"};
        Intent emailIntent = new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JSON");
        emailIntent.putExtra(Intent.EXTRA_TEXT, jsonContacts);

        try{
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(webapp.this, "there is no email client", Toast.LENGTH_SHORT).show();
        }
    }
