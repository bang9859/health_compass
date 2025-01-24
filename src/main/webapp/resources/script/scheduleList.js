document.addEventListener("DOMContentLoaded", function () {
    try {
        // JSP에서 전달된 JSON 데이터를 가져옴
        const scheduleList = JSON.parse('<%= new org.json.JSONArray(scheduleListJson).toString() %>');

        if (scheduleList && scheduleList.length > 0) {
            // 일정 데이터를 테이블에 추가
            const scheduleTable = document.getElementById("schedule-list").querySelector("tbody");

            scheduleList.forEach(schedule => {
				
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${schedule.medicineName || "정보 없음"}</td>
                    <td>${schedule.depositMethod || "정보 없음"}</td>
                    <td>${schedule.startDate}</td>
                    <td>${schedule.endDate}</td>
                    <td>${schedule.dailyFrequency}</td>
                `;
                scheduleTable.appendChild(row);
                console.log("scheduleList:", scheduleList);
            });
        } else {
            // 데이터가 없을 경우
            const noDataRow = document.createElement("tr");
            noDataRow.innerHTML = `<td colspan="5">일정이 없습니다.</td>`;
            scheduleTable.appendChild(noDataRow);
        }
    } catch (error) {
        console.error("Error parsing schedule JSON:", error);
        alert("일정을 불러오는 데 실패했습니다.");
    }
});
