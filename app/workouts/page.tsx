"use client";

import { useEffect, useState } from "react";
import axios from "axios";
import DashboardNavbar from "@/components/dashboard/DashboardNavbar";

export default function WorkoutsPage() {

    const [workouts, setWorkouts] = useState([]);

    useEffect(() => {

        const token = localStorage.getItem("token");

        axios.get(
            "http://localhost:8080/api/workouts/user/1",
            {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        )
            .then((response) => {
                setWorkouts(response.data);
            })
            .catch((error) => {
                console.log(error);
            });

    }, []);

    return (

        <>

            <DashboardNavbar />

            <main className="min-h-screen bg-[#0a0a0a] text-white p-8">

                <h1 className="text-4xl font-bold mb-8">
                    Workouts
                </h1>

                <div className="overflow-x-auto">

                    <table className="w-full border border-white/10">

                        <thead className="bg-white/5">

                        <tr>

                            <th className="p-4 text-left">
                                Exercise
                            </th>

                            <th className="p-4 text-left">
                                Sets
                            </th>

                            <th className="p-4 text-left">
                                Reps
                            </th>

                            <th className="p-4 text-left">
                                Weight
                            </th>

                        </tr>

                        </thead>

                        <tbody>

                        {workouts.map((workout: any) => (

                            <tr
                                key={workout.id}
                                className="border-t border-white/10"
                            >

                                <td className="p-4">
                                    {workout.exerciseName}
                                </td>

                                <td className="p-4">
                                    {workout.sets}
                                </td>

                                <td className="p-4">
                                    {workout.reps}
                                </td>

                                <td className="p-4">
                                    {workout.weight}
                                </td>

                            </tr>

                        ))}

                        </tbody>

                    </table>

                </div>

            </main>

        </>
    );
}