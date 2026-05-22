"use client";
import { useEffect, useState } from "react";
import axios from "axios";

import Navbar from "@/components/Navbar";
import Hero from "@/components/Hero";
import Features from "@/components/Features";
import Stats from "@/components/Stats";
import CTA from "@/components/CTA";
import Footer from "@/components/Footer";

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
      <main className="min-h-screen bg-[#080808] text-white overflow-x-hidden">
        <Navbar />
        <Hero />
        <Stats />
        <Features />
        <CTA />
        <Footer />
      </main>
  );
}