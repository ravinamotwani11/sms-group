import React, { Component } from 'react';
import ReactTable from "react-table";
import "react-table/react-table.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { serviceCall } from '../Services/serviceCall';
import { Formik, Form, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import deleteIcon from "../images/delete-icon.png";
import editIcon from "../images/edit-icon.png";

class CityList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            response: [],
            responseForFilter: [],
            filtered: [],
            startDate: "",
            endDate: "",
            city: "",
            start_date: "",
            end_date: "",
            status: "",
            color: "",
            price: "",
            showForm: false,
            updateState: false,
        };

    }

    handleStartDateChange = date => {

        this.setState({
            startDate: date
        });

    };

    handleEndDateChange = date => {

        this.setState({
            endDate: date
        });

    };

    searchDate = () => {

        // All the records from api call
        var responseData = this.state.responseForFilter

        var dateWiseFilteredList = [];

        (responseData || []).map(resList => {

            var resstartDate = new Date(resList.start_date);
            // var resEndDate=new Date(resList.end_date);

            // To selected date
            var selectedEndDate = new Date(this.state.endDate);

            // From selected date
            var selectedStartDate = new Date(this.state.startDate);

            if (resstartDate >= selectedStartDate && resstartDate <= selectedEndDate) {
                dateWiseFilteredList.push(resList);
            }
            this.setState({ response: dateWiseFilteredList })
        });
    }

    // hide form on cancel button
    _hideForm = (e) => {
        this.setState({ showForm: false });
        window.location.reload();
    };

    _handleSubmit = (values, actions) => {

        var postvalues =
            [
                {
                    "id": values.id,
                    "city": values.city,
                    "status": values.status,
                    "start_date": values.start_date,
                    "end_date": values.end_date,
                    "color": values.color,
                    "price": values.price,
                }
            ]


        if (this.state.updateState === true) {
            var postvalues =
                [
                    {
                        "id": this.state.id,
                        "city": values.city,
                        "status": values.status,
                        "start_date": values.start_date,
                        "end_date": values.end_date,
                        "color": values.color,
                        "price": values.price,
                    }
                ]

            serviceCall.editRecord(postvalues, (response) => {

                if (response.data.reasonCode === "200") {
                    this.setState({ updateState: false });
                    alert(response.data.reasonText)
                    window.location.reload();
                }
                else {
                    alert(response.data.reasonText)
                }
            })
        } else {
            serviceCall.addRecord(postvalues, (response) => {

                if (response.data.reasonCode === "200") {

                    alert(response.data.reasonText)
                    window.location.reload();
                }
                else {
                    alert(response.data.reasonText)
                }
            })
        }
    };


    componentDidMount() {

        serviceCall.fetchList((response) => {

            if (response.data.reasonCode === "200") {

                this.setState({
                    response: response.data.responseListObject,
                    responseForFilter: response.data.responseListObject
                })
            }
        })

    }

    render() {
        const cityList = this.state.response;                                 //get data from state data object
        const columns = [{                                              //columns
            Header: 'city',                            // column name
            accessor: 'city'                                   //access data into column from data object(accessor name must be same as field name that we want access from data object)
        },
        {
            id: 'start date',
            Header: 'start date',
            accessor: 'start_date',

        },
        {
            id: 'end date',
            Header: 'end date',
            accessor: 'end_date',

        },
        {
            Header: 'price',
            accessor: 'price'
        },
        {
            Header: 'status',
            accessor: 'status'
        },
        {
            Header: 'color',
            accessor: 'color'
        },

        {
            Header: () => (
                <div
                    style={{
                        textAlign: "center",
                    }}
                >
                    Action
                </div>
            ),

            id: "delete",
            accessor: (str) => "delete",

            Cell: (row) => (
                <div>
                    <div style={{ textAlign: "center" }}>
                        <span
                            style={{
                                display: "inline",
                                cursor: "pointer",
                                color: "blue",
                                textDecoration: "underline",
                            }}
                            onClick={(e) => {

                                this.setState({ showForm: true })
                            }}>
                            Add
                  </span>
                  &nbsp; &nbsp; &nbsp; &nbsp;
                  <span
                            style={{
                                display: "inline",
                                cursor: "pointer",
                                color: "blue",
                                textDecoration: "underline",
                            }}
                            onClick={(e) => {

                                this.setState({
                                    id: this.state.responseForFilter[row.index].id,
                                    city: this.state.responseForFilter[row.index].city,
                                    start_date: this.state.responseForFilter[row.index].start_date,
                                    end_date: this.state.responseForFilter[row.index].end_date,
                                    color: this.state.responseForFilter[row.index].color,
                                    price: this.state.responseForFilter[row.index].price,
                                    status: this.state.responseForFilter[row.index].status,
                                    updateState: true,
                                    showForm: true,
                                })
                            }}>
                            <img src={editIcon} />
                        </span>
                  &nbsp; &nbsp; &nbsp; &nbsp;
                  <span
                            style={{ display: "inline", cursor: "pointer" }}
                            onClick={() => {
                                const Id = this.state.responseForFilter[row.index].id;
                                const id = {
                                    "id": Id
                                }
                                serviceCall.deleteRecord(id, (response) => {

                                    if (response.data.reasonCode === "200") {

                                        alert(response.data.reasonText)
                                        window.location.reload();
                                    }
                                    else {
                                        alert(response.data.reasonText)
                                    }
                                })
                            }}
                        >
                            <img src={deleteIcon} />
                        </span>
                    </div>
                </div>
            ),
        },
        ]

        return (

            <div>

                {this.state.showForm ?
                    (
                        <div>

                            <div>Add or Edit Record</div>

                            <div>
                                <Formik
                                    enableReinitialize={true}
                                    initialValues={{
                                        city: this.state.city,
                                        start_date: this.state.start_date,
                                        end_date: this.state.end_date,
                                        price: this.state.price,
                                        status: this.state.status,
                                        color: this.state.color,

                                    }}
                                    validationSchema={this._validationSchema}
                                    onSubmit={this._handleSubmit}
                                >
                                    {({
                                        touched,
                                        errors,
                                        setFieldValue,
                                        values,
                                        isSubmitting,
                                    }) => (
                                            <Form>
                                                <div>

                                                    <label htmlFor="">
                                                        City
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="city"
                                                        placeholder="City"
                                                        className="form-control"
                                                        required
                                                    />

                                                </div>

                                                <div>
                                                    <label htmlFor="">
                                                        Start Date
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="start_date"
                                                        placeholder="MM/DD/YYYY"
                                                        className="form-control"
                                                    />

                                                </div>

                                                <div>
                                                    <label htmlFor="">
                                                        End Date
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="end_date"
                                                        placeholder="MM/DD/YYYY"
                                                        className="form-control"
                                                    />

                                                </div>

                                                <div>

                                                    <label htmlFor="">
                                                        Price
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="price"
                                                        placeholder="Price"
                                                        className="form-control"
                                                    />

                                                    <ErrorMessage
                                                        component="div"
                                                        name="price"
                                                        className="text-danger"
                                                    />
                                                </div>

                                                <div>

                                                    <label htmlFor="">
                                                        Status
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="status"
                                                        placeholder="Status"
                                                        className="form-control"
                                                    />

                                                </div>

                                                <div>

                                                    <label htmlFor="">
                                                        Color
                          </label>

                                                    <Field
                                                        type="text"
                                                        name="color"
                                                        placeholder="Color"
                                                        className="form-control"
                                                    />

                                                </div>

                                                <button
                                                    onClick={this._hideForm}
                                                >
                                                    Cancel
                      </button>
                                                <button
                                                    type="submit"
                                                >
                                                    SAVE
                      </button>
                                                <div className="clearfix" />
                                            </Form>
                                        )}
                                </Formik>
                            </div>
                        </div>) : null}

                <br />

                <div className="datepicker">
                    <div className="pr">
                        <label htmlFor="">From</label>
                            &nbsp;

                            <DatePicker

                            autoComplete="off"
                            placeholderText="mm-dd-yyyy"
                            dateFormat="MM-dd-yyyy"
                            className="form-control"
                            selected={this.state.startDate}
                            onChange={this.handleStartDateChange}
                        />
                    </div>

                    <div className="pr">
                        <label htmlFor="">To</label>
                            &nbsp;
                            <DatePicker

                            placeholderText="mm-dd-yyyy"
                            dateFormat="MM-dd-yyyy"
                            maxLength="50"
                            className="form-control"
                            selected={this.state.endDate}
                            onChange={this.handleEndDateChange}
                            minDate={this.state.startDate}
                        />
                    </div>
                    <div>
                        <button type="button" style={{ outline: 'none', }}
                            onClick={this.searchDate}
                        >Search</button>
                    </div>

                </div>


                <div className="container">
                    <div className="re-white-bg">

                        <div id="table">

                            <ReactTable
                                data={this.state.response}                                                // bind data on table
                                columns={columns}                                                       // bind column to grid
                                defaultPageSize={10}                                                      //by Default row size
                            />
                        </div>
                    </div>
                </div>
            </div>

        )

    }
}
export default CityList;