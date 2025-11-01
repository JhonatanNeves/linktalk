package com.example.linktalk.ui.validator

interface FormValidator <FormState>{
    fun validate(formState: FormState) : FormState
}