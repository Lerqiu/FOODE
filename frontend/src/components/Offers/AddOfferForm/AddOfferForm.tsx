import { Formik } from 'formik';
import {
  Button,
  Form, FormControl, FormLabel, InputGroup,
} from 'react-bootstrap';
import * as yup from 'yup';
// eslint-disable-next-line import/no-named-default
import { default as ReactDatePicker } from 'react-datepicker';
import { CheckLg as AcceptIcon } from 'react-bootstrap-icons';
import React from 'react';
import ICity from '../../../interfaces/city/ICity';

const EMPTY_INPUT_MSG = 'Pole wymagane!';
const INPUT_TOO_LONG = 'Podane pole jest za długie';

const today = new Date();

function AddOfferForm(props: {cities: ICity[], submit: (values: any) => void}) {
  const { cities, submit } = props;

  const schema = yup.object().shape({
    productName: yup.string()
      .max(255, INPUT_TOO_LONG)
      .required(EMPTY_INPUT_MSG),
    price: yup.number()
      .required(EMPTY_INPUT_MSG),
    city: yup.string()
      .required(EMPTY_INPUT_MSG),
    description: yup.string()
      .max(4000, INPUT_TOO_LONG)
      .required(EMPTY_INPUT_MSG),
    availability: yup.string()
      .max(255, INPUT_TOO_LONG)
      .required(EMPTY_INPUT_MSG),
    expirationDate: yup.date()
      .default(today)
      .min(today),
  });

  return (
    <Formik
      validationSchema={schema}
      onSubmit={(values, actions) => {
        submit(values);
        actions.setSubmitting(false);
      }}
      initialValues={{
        productName: '',
        price: '',
        description: '',
        availability: '',
        expirationDate: today,
        city: '',
      }}
    >
      {({
        handleChange,
        handleSubmit,
        values,
        errors,
        setFieldValue,
      }) => (
        <Form
          onSubmit={(e) => {
            e.preventDefault();
            handleSubmit(e);
          }}
        >
          <FormLabel>Nazwa produktu</FormLabel>
          <InputGroup>
            <FormControl
              name="productName"
              value={values.productName}
              onChange={handleChange}
              isInvalid={!!errors.productName}
              placeholder="nazwa produktu"
            />
            <Form.Control.Feedback type="invalid" tooltip>{errors.productName}</Form.Control.Feedback>
          </InputGroup>
          <FormLabel>Cena</FormLabel>
          f
          <InputGroup>
            <FormControl
              name="price"
              value={values.price}
              isInvalid={!!errors.price}
              onChange={handleChange}
              placeholder="Cena"
            />
            <Form.Control.Feedback type="invalid" tooltip>{errors.price}</Form.Control.Feedback>
          </InputGroup>
          <FormLabel>Miasto</FormLabel>
          <InputGroup>
            <Form.Select
              defaultValue="PLACEHOLDER"
              onChange={handleChange}
              name="city"
            >
              <option value="">
                Wybierz miasto
                {' '}
              </option>
              {cities ? cities.map((_city) => (
                <option key={_city.id} value={JSON.stringify(_city)}>
                  {_city.name}
                </option>
              )) : ''}
            </Form.Select>
            {errors.city && <div className="input-feedback">{errors.city}</div>}
          </InputGroup>
          <Form.Group
            className="mb-3"
            controlId="exampleForm.ControlTextarea1"
          >
            <Form.Label>Opis</Form.Label>
            <InputGroup>
              <Form.Control
                name="description"
                as="textarea"
                rows={3}
                value={values.description}
                isInvalid={!!errors.description}
                onChange={handleChange}
                placeholder="Opis"
              />
              <Form.Control.Feedback type="invalid" tooltip>{errors.description}</Form.Control.Feedback>
            </InputGroup>

          </Form.Group>
          <FormLabel>Dostępność</FormLabel>
          <InputGroup>
            <FormControl
              name="availability"
              value={values.availability}
              onChange={handleChange}
              isInvalid={!!errors.availability}
              placeholder="dostępność"
            />
            <Form.Control.Feedback type="invalid" tooltip>{errors.availability}</Form.Control.Feedback>
          </InputGroup>
          <FormLabel>Data ważności</FormLabel>
          <InputGroup>
            <ReactDatePicker
              name="expirationDate"
              dateFormat="yyyy-MM-dd"
              showPopperArrow={false}
              minDate={new Date()}
              selected={values.expirationDate}
              onChange={((date) => setFieldValue('expirationDate', date))}
              useWeekdaysShort
              fixedHeight
            />
            <Form.Control.Feedback type="invalid" tooltip>{errors.expirationDate}</Form.Control.Feedback>
          </InputGroup>

          <Button
            type="submit"
            variant="outline-success"
          >
            <AcceptIcon />
          </Button>
        </Form>
      )}
    </Formik>
  );
}

export default AddOfferForm;
