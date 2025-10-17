const analyzeBtn = document.getElementById("analyzeBtn");
const resultDiv = document.getElementById("result");

analyzeBtn.addEventListener("click", () => {
    const jobDescription = document.getElementById("jobDescription").value;
    const resumeFile = document.getElementById("resumeFile").files[0];

    if (!jobDescription || !resumeFile) {
        alert("Please enter a job description and select a resume.");
        return;
    }

    const formData = new FormData();
    formData.append("jobDescription", jobDescription);
    formData.append("resumeFile", resumeFile);

    fetch("http://localhost:8080/api/analyze", {
        method: "POST",
        body: formData
    })
    .then(res => res.json())
    .then(data => {
        resultDiv.textContent = `Score: ${data.score}, Keywords: ${data.matchedKeywords}`;
        loadHistory(); // refresh history
    })

    .catch(err => {
        console.error(err);
        resultDiv.textContent = "Error analyzing resume.";
    });
});

function loadHistory() {
    fetch("http://localhost:8080/api/results")
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#historyTable tbody");
            tbody.innerHTML = "";
            data.forEach(item => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${item.resumeName || "N/A"}</td>
                    <td>${item.jobDescription || "N/A"}</td>
                    <td>${item.score || 0} / ${item.matchedKeywords || "None"}</td>
                    <td>${new Date(item.analyzedAt).toLocaleString()}</td>
                `;
                tbody.appendChild(row);
            });
        })
        .catch(err => console.error(err));
}

window.onload = loadHistory;
