"use client";

import { useEffect, useState } from "react";
import axios from "axios";

export default function Home() {

  const [dashboard, setDashboard] = useState({
    totalWorkouts: 0,
    totalVolume: 0,
    latestWeight: 0,
    recentWorkoutCount: 0,
    latestExercise: "",
  });

  useEffect(() => {

    axios
        .get("http://localhost:8080/api/dashboard/1")
        .then((response) => {
          setDashboard(response.data);
        })
        .catch((error) => {
          console.error(error);
        });

  }, []);

  return (

      <main className="min-h-screen bg-gray-100 p-8">

        <h1 className="text-4xl font-bold mb-6">
          FitTrackPro Dashboard
        </h1>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">

          <div className="bg-white p-6 rounded-2xl shadow">
            <h2 className="text-xl font-semibold">
              Total Workouts
            </h2>

            <p className="text-3xl mt-4">
              {dashboard.totalWorkouts}
            </p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h2 className="text-xl font-semibold">
              Latest Weight
            </h2>

            <p className="text-3xl mt-4">
              {dashboard.latestWeight} kg
            </p>
          </div>

          <div className="bg-white p-6 rounded-2xl shadow">
            <h2 className="text-xl font-semibold">
              Total Volume
            </h2>

            <p className="text-3xl mt-4">
              {dashboard.totalVolume}
            </p>
          </div>

        </div>

      </main>
  );
}