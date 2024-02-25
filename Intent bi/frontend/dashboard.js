const url = "http://localhost:8080";
const baseUrl = "http://localhost:8080";
let headers;
let sortBy = "id";
let direction = "asc";
const itemsPerPage = 20;
let currentPage = 1;
let allData = []; // Variable to store all fetched data

document.addEventListener("DOMContentLoaded", function () {
  fetchDataAndRender();

  async function fetchDataAndRender() {
    try {
      const data = await fetchData();
      console.log("data", data);
      if (data) {
        allData = data; // Store all fetched data
        renderTableRows(getCurrentPageData());
        renderPagination();
      } else {
        console.error("Data is undefined");
        alert("Failed to fetch data");
      }
    } catch (error) {
      console.error("Error fetching data:", error);
      alert("Failed to fetch data");
    }
  }

  async function fetchData() {
    let apiUrl = `${baseUrl}/data?page=0&size=${
      itemsPerPage * 10
    }&sortBy=${sortBy}&direction=${direction}`; // Fetch all data at once
    const response = await fetch(apiUrl);
    const data = await response.json();
    headers = await response.headers; // Get headers from the response
    return data;
  }

  //   function renderTableRows(data) {
  //     let tableBody = document.getElementById("table-body");
  //     tableBody.innerHTML = "";
  //     data.forEach((item) => {
  //       const row = document.createElement("tr");
  //       row.innerHTML = generateTableCells(item);
  //       tableBody.appendChild(row);
  //     });
  //   }
  //   function renderTableRows(data) {
  //     const tableBody = document.getElementById("table-body");
  //     tableBody.innerHTML = "";
  //     data.forEach((item) => {
  //       const row = document.createElement("tr");
  //       row.innerHTML = generateTableCells(item);

  //       const editButton = document.createElement("button");
  //       editButton.textContent = "Edit";
  //       editButton.classList.add("edit-button");
  //       editButton.dataset.id = item.id; // Assuming 'id' is the unique identifier
  //       row.appendChild(editButton);

  //       const deleteButton = document.createElement("button");
  //       deleteButton.textContent = "Delete";
  //       deleteButton.classList.add("delete-button");
  //       deleteButton.dataset.id = item.id; // Assuming 'id' is the unique identifier
  //       row.appendChild(deleteButton);

  //       tableBody.appendChild(row);
  //     });
  //   }
  function renderPagination() {
    const totalPages = Math.ceil(allData.length / itemsPerPage);
    const paginationContainer = document.getElementById("pagination-container");
    paginationContainer.innerHTML = "";

    for (let i = 1; i <= totalPages; i++) {
      const button = document.createElement("button");
      button.textContent = i;
      button.classList.add("pagination-button");
      button.addEventListener("click", function () {
        currentPage = i;
        renderTableRows(getCurrentPageData());
      });
      paginationContainer.appendChild(button);
    }
  }

  function getCurrentPageData() {
    const startIndex = (currentPage - 1) * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    return allData.slice(startIndex, endIndex);
  }

  function generateTableCells(item) {
    const cells = Object.values(item).map((value) => `<td>${value}</td>`);
    return cells.join("");
  }
  // Function to render table rows with edit and delete buttons
  function renderTableRows(data) {
    const tableBody = document.getElementById("table-body");
    tableBody.innerHTML = "";
    data.forEach((item) => {
      const row = document.createElement("tr");
      row.innerHTML = generateTableCells(item);

      // Add event listener to each cell
      row.querySelectorAll("td").forEach((cell) => {
        cell.addEventListener("click", function () {
          console.log("Cell clicked");
          const currentValue = cell.textContent.trim();
          cell.innerHTML = `<input type="text" class="editable-cell" value="${currentValue}">`;
          cell.querySelector("input").focus();
        });
      });

      tableBody.appendChild(row);
    });
  }

  // Event listener for clicks on the document
  document.addEventListener("click", function (event) {
    const target = event.target;
    const isEditableCell = target.classList.contains("editable-cell");

    if (!isEditableCell) {
      const editedCell = document.querySelector(".editable-cell");
      if (editedCell) {
        const newValue = editedCell.value.trim();
        const row = editedCell.parentNode;
        const editedRow = editedCell.parentNode.parentNode;
        const cells = Array.from(editedRow.cells);
        const cellIndex = cells.indexOf(row);
        console.log("rowCHILDRENS", row.children);
        const rowData = {
          id: editedRow.children[0].textContent.trim(),
          market: editedRow.children[1].textContent.trim(),
          country: editedRow.children[2].textContent.trim(),
          product: editedRow.children[3].textContent.trim(),
          discountBand: editedRow.children[4].textContent.trim(),
          unitsSold: editedRow.children[5].textContent.trim(),
          manufacturingPrice: editedRow.children[6].textContent.trim(),
          salePrice: editedRow.children[7].textContent.trim(),
          grossSales: editedRow.children[8].textContent.trim(),
          sales: editedRow.children[9].textContent.trim(),
          cogs: editedRow.children[10].textContent.trim(),
          profit: editedRow.children[11].textContent.trim(),
          date: editedRow.children[12].textContent.trim(),
          monthNumber: editedRow.children[13].textContent.trim(),
          monthName: editedRow.children[14].textContent.trim(),
          year: editedRow.children[15].textContent.trim(),
        };

        rowData[Object.keys(rowData)[cellIndex]] = newValue;
        console.log("rowData", rowData);
        updateData(rowData);
        editedCell.parentNode.textContent = newValue;
      }
    }
  });

  //   document.addEventListener("click", function (event) {
  //     const target = event.target;
  //     const isEditableCell = target.classList.contains("editable-cell");

  //     if (!isEditableCell) {
  //       const editedCell = document.querySelector(".editable-cell");
  //       if (editedCell) {
  //         const newValue = editedCell.value.trim();
  //         const row = editedCell.parentNode.parentNode;
  //         console.log("row", row);
  //         const cells = row.cells;

  //         const rowData = {};

  //         for (let i = 0; i < cells.length; i++) {
  //           const cell = cells[i];
  //           const header = document.querySelector(
  //             `#data-table th:nth-child(${i + 1})`
  //           ).textContent;
  //           rowData[header] = cell.textContent.trim();
  //         }

  //         const editedCellIndex = Array.from(cells).indexOf(editedCell);
  //         const header = document.querySelector(
  //           `#data-table th:nth-child(${editedCellIndex + 1})`
  //         ).textContent;
  //         rowData[header] = newValue;

  //         updateData(rowData);
  //         editedCell.parentNode.textContent = newValue;
  //       }
  //     }
  //   });

  async function updateData(rowData) {
    const id = rowData.id; // Extract the ID from the rowData object
    delete rowData.id; // Remove the ID property from the data to be sent to the API

    try {
      const response = await fetch(`http://localhost:8080/update/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(rowData),
      });

      if (response.ok) {
        // Data updated successfully
        console.log("Data updated successfully");
      } else {
        // Error updating data
        console.error("Failed to update data");
        alert("Failed to update data");
      }
    } catch (error) {
      console.error("Error updating data:", error);
      alert("Error updating data");
    }
  }
});
